Vue.component("facilities", {
    data: function() {
    return {
        facilities: [],
        name: '',
        location: '',
        type: '',
    }
},
template: `
    <div class="content">
        <h1>Facilities</h1>
        <div class="search-box">
            <div class="input-group search-field">
                <div class="input-group-prepend">
                    <span class="input-group-text">Name</span>
                </div>
                <input type="text"  class="form-control" v-model="name">
            </div>
            <div class="input-group search-field">
                <div class="input-group-prepend">
                    <span class="input-group-text">Location</span>
                </div>
                <input type="text"  class="form-control" v-model="location">
            </div>
            <div class="input-group search-field">
                <div class="input-group-prepend">
                    <label class="input-group-text">Type</label>
                </div>
                <select class="custom-select" v-model="type">
                    <option></option>
                    <option>GYM</option>
                    <option>SWIMMING POOL</option>
                    <option>SPORT VENUE</option>
                    <option>DANCE CENTER</option>
                </select>
            </div>
            <button type="button" class="btn btn-light search-field" v-on:click="search">
                <i class="fa fa-search"></i>
            </button>
        </div>
        <div class="facilities-content">
            <facility-card v-for="item in facilities" v-bind:facility="item" v-bind:key="item.id"></facility-card>
        </div>
    </div>
`,
methods: {
    search: function() {
        var typeF = this.type.trim();
        typeF = typeF.replace(/ /g, "_");
        axios.get('facilities/search', {params: {
            name : this.name,
            location: this.location,
            type: typeF,
        }})
        .then(res => {
            this.facilities = res.data;
        })
    }
},
mounted() {
    axios.get('/facilities')
    .then(res => {
        this.facilities = res.data;
    })
},
});