Vue.component("facilities", {
    data: function() {
    return {
        facilities: [],
    }
},
template: `
    <div>
        <h1>Facilities</h1>
        <div class="facilities-content">
            <facility-card v-for="item in facilities" v-bind:facility="item" v-bind:key="item.id"></facility-card>
        </div>
    </div>
`,
methods: {
    
},
mounted() {
    axios.get('/facilities')
    .then(res => {
        this.facilities = res.data;
    })
},
});