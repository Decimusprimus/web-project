Vue.component("facility-new", {
    data: function() {
    return {
        a : null,
        name: '',
        type: '',
        location: '',
        logo: [],
        filePreview: null,
        streetName: '',
        streetNumber: '',
        city: '',
        postalCode: '',
        managers: [],
        manager: [],
        selectedManager: [],

    }
},
template: `
    <div class="container" style="margin-bottom: 100px;">
        <h2>Create new facility</h2>
        <form>
            <div class="form-group">
                <label for="facilityName">Name</label>
                <input type="text" class="form-control" id="facilityName" v-model="name">
            </div>
            <div class="form-group">
                <label>Type</label>
                <select class="form-control" v-model="type">
                    <option>GYM</option>
                    <option>SWIMMING POOL</option>
                    <option>SPORT VENUE</option>
                    <option>DANCE CENTER</option>
                </select>
            </div>
            <hr/>
            <h4>Location</h4>
            <div class="location-container">
                <location-map v-on:selectedLocation="updateLocation"></location-map>
                <div >
                    <div class="form-row" >
                        <div class="col-md-6 mb-3">
                            <label for="locStreetName">Street name</label>
                            <input type="text" class="form-control" id="locStreetName" v-model="streetName" disabled>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="locStreetNumber">Street number</label>
                            <input type="text" class="form-control" id="locStreetNumber" v-model="streetNumber" disabled>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-6 mb-3">
                            <label for="locCity">City</label>
                            <input type="text" class="form-control" id="locCity" v-model="city" disabled>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="locPostalCode">Postal code</label>
                            <input type="text" class="form-control" id="locPostalCode" v-model="postalCode" disabled>
                        </div>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="form-group">

                <label for="facilityLogo">Logo image</label>
                <div>
                    <div class="previewBlock" v-bind:style="logoImage">  
                    </div>
                    <input type="file" ref="fileInput"  accept="image/jpeg, image/png, image/jpg" class="form-control form-control" id="facilityLogo" v-on:input="selectImgFile">
                </div>
            </div>
            <hr/>
            <h4>Select manager</h4>
            <div>
                <div class="form-group">
                    <label>Manager</label>
                    <select class="form-control" v-model="manager">
                        <option v-for="item in managers" v-on:click="selectChange(item)">{{getManagerString(item)}}</option>
                    </select>
                </div>
                <button class="btn btn-primary" data-toggle="modal" data-target="#registerManagerModal">Register new manager</button>
                <register-manager id="registerManagerModal" v-on:newManager="selectManger"></register-manager>
            </div>
        </form>

    </div>
`,
methods: {
    choseFile: function() {
        this.$refs.fileInput.click();
    },
    selectImgFile: function() {
        let fileInput = this.$refs.fileInput
        let imgFile = fileInput.files

        if (imgFile && imgFile[0]) {
            let reader = new FileReader();
            reader.onload = e => {
                this.filePreview = e.target.result
            }
            reader.readAsDataURL(imgFile[0])
            this.$emit('fileInput', imgFile[0])
            
        }
    },
    updateLocation: function(item) {
        this.streetName = item.address.road;
        this.streetNumber = item.address.house_number;
        this.city = item.address.city;
        this.postalCode = item.address.postcode;
    },
    getFreeManagers: function() {
        axios.get("/users/manager/free")
        .then(res => {
            this.managers = res.data;
        })
    },
    getManagerString(m) {
        return m.firstName + ' ' +  m.lastName + ', ' + m.username;
    },
    selectManger: function(m) {
        this.managers.push(m);
        this.manager = this.getManagerString(m);
        this.selectManger = m;
    },
    selectChange(item) {
        this.selectedManager = item;
    }
},
mounted() {
    this.getFreeManagers();
},
computed: {
    logoImage () {
        if(this.filePreview){
            return {
                backgroundImage: `url(${this.filePreview})`
            }
        } else {
            return {
                backgroundImage: '#c3c6c432'
            }
        }
    }
}
});