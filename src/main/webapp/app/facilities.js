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
    },
    
    checkIsOpen: function(facility) {
        var today = new Date(); 
        var i = today.getDay()-1;
        if(i == -1) {
            i = 6;
        }
        var workHour = facility.workingHours[i];
        var from = workHour.from.split(":");
        var to = workHour.to.split(":");
        var fromDate = new Date();
        if(from[0] === '--' || from[1] === '--' || to[0] === '--' || to[1] === '--') {
            return false;
        }

        fromDate.setHours(parseInt(from[0]), parseInt(from[1]));
        var toDate = new Date();
        toDate.setHours(parseInt(to[0]), parseInt(to[1]));
        var isOpen = false;
        if(fromDate.getTime() === toDate.getTime()) {
            isOpen = true;
        } else if(fromDate.getTime() <= today.getTime() && toDate.getTime() >= today.getTime() ) {
            isOpen = true;
        } else {
            isOpen = false;
        }
        return isOpen;
    },
},
mounted() {
    axios.get('/facilities')
    .then(res => {
        this.facilities = res.data;
        this.facilities.sort((a, b) => {
            if(this.checkIsOpen(a) && !this.checkIsOpen(b)) {
                return -1;
            } else if(this.checkIsOpen(a) && this.checkIsOpen(b)) {
                return 0;
            }
            return 1;
        })
    })
},
});