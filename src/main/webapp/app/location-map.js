Vue.component("location-map", {
    data: function() {
    return {
        searchQuery: '',
        searchResult: [],
        markers: [],
        map: [],
        selectedPin: [],
    }
},
template: `
    <div class="map-container">
        <div class="map-search">
            <input type="text" class="form-control" placeholder="Search" v-model="searchQuery" v-on:keyup.enter="search">
            <div v-for="item in searchResult" >
                <div class="map-search-result" v-on:click="selectLocation(item)">
                    <h6>{{item.display_name}}</h6>
                </div>
            </div>
        </div>
        <div id="map" class="map">
        </div>
    </div>
`,
methods: {
    search: function() {
        console.log(this.searchQuery);
        axios.get('https://nominatim.openstreetmap.org', { params: {
            addressdetails: 1,
            q: this.searchQuery,
            format: 'json',
            limit: 3,
        }})
        .then(res => {
            this.searchResult = res.data;
        })
    },
    removeSelected: function() {
        this.markers.getSource().clear();
    },
    selectLocation: function(item) {
        this.removeSelected();
        const itemLonLat = [ item.lon, item.lat]
        var marker = new ol.Feature(new ol.geom.Point(ol.proj.fromLonLat(itemLonLat)));
        this.selectedPin = marker;
        this.markers.getSource().addFeature(marker);
        this.map.getView().fit(marker.getGeometry(), {padding: [170, 50, 30, 150 ], minResolution: 10, duration: 1000})
        this.$emit('selectedLocation', item);
    }
},
mounted() {
    const serbiaLonLat = [21.0059, 44.0168]
     this.map = new ol.Map({
        target: 'map',
        layers: [
            new ol.layer.Tile({
              source: new ol.source.OSM()
            })
        ],
        view: new ol.View({
            center: ol.proj.fromLonLat(serbiaLonLat),
            zoom: 6.5
        })
    })

    this.markers = new ol.layer.Vector({
        source: new ol.source.Vector(),
        style: new ol.style.Style({
            image:new ol.style.Icon({
                anchor: [0.5, 1],
                src: 'images/marker.png',
            })
        })
    });
    this.map.addLayer(this.markers);
    
},
});