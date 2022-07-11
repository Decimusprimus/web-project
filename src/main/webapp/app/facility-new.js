Vue.component("facility-new", {
    data: function() {
    return {
        name: '',
        type: '',
        location: '',
        logo: [],
        filePreview: null,
        streetName: '',
        streetNumber: '',
        lon: '',
        lat: '',
        city: '',
        postalCode: '',
        managers: [],
        manager: '',
        selectedManager: [],
        time: '',
        workingHours : [
            {day : "Mon.", fromH: "00", fromM: "00", toH: "00", toM: "00" },
            {day : "Tue.", fromH: "00", fromM: "00", toH: "00", toM: "00" },
            {day : "Wed.", fromH: "00", fromM: "00", toH: "00", toM: "00" },
            {day : "Thu.", fromH: "00", fromM: "00", toH: "00", toM: "00" },
            {day : "Fri.", fromH: "00", fromM: "00", toH: "00", toM: "00" },
            {day : "Sat.", fromH: "00", fromM: "00", toH: "00", toM: "00" },
            {day : "Sun.", fromH: "00", fromM: "00", toH: "00", toM: "00" }
        ],
        hours: ["--", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"],
        minutes: ["--", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28","29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"],
        submitted: false,
        success: false
    }
},
template: `
    <div class="container" style="margin: auto; margin-bottom: 200px; margin-top: 50px; width: 80%;">
        <div v-if="!success">
            <h2>Create new facility</h2>
            <form>
                <div class="form-group">
                    <label for="facilityName">Name</label>
                    <input type="text" class="form-control" id="facilityName" v-model="name" v-bind:class="{'is-invalid' : !validName() && submitted}" required>
                    <div class="invalid-feedback">Name is requeued</div>
                </div>
                <div class="form-group">
                    <label>Type</label>
                    <select class="form-control" v-model="type" v-bind:class="{'is-invalid' : !type && submitted}" required>
                        <option>GYM</option>
                        <option>SWIMMING POOL</option>
                        <option>SPORT VENUE</option>
                        <option>DANCE CENTER</option>
                    </select>
                    <div class="invalid-feedback">Type is requeued</div>
                </div>
                <hr/>
                <h4>Location</h4>
                <div class="location-container">
                    <location-map v-on:selectedLocation="updateLocation"></location-map>
                    <div >
                        <div class="form-row" >
                            <div class="col-md-6 mb-3">
                                <label for="locStreetName">Street name</label>
                                <input type="text" class="form-control" id="locStreetName" v-model="streetName" v-bind:class="{'is-invalid' : !streetName && submitted}" disabled>
                                <div class="invalid-feedback">Location not set</div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="locStreetNumber">Street number</label>
                                <input type="text" class="form-control" id="locStreetNumber" v-model="streetNumber" v-bind:class="{'is-invalid' : !streetNumber && submitted}" disabled>
                                <div class="invalid-feedback">Location not set</div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-6 mb-3">
                                <label for="locCity">City</label>
                                <input type="text" class="form-control" id="locCity" v-model="city" v-bind:class="{'is-invalid' : !city && submitted}" disabled>
                                <div class="invalid-feedback">Location not set</div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="locPostalCode">Postal code</label>
                                <input type="text" class="form-control" id="locPostalCode" v-model="postalCode" v-bind:class="{'is-invalid' : !postalCode && submitted}" disabled>
                                <div class="invalid-feedback">Location not set</div>
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
                        <input type="file" ref="fileInput"  accept="image/jpg" class="form-control" id="facilityLogo" v-on:input="selectImgFile" v-bind:class="{'is-invalid' : !filePreview && submitted}">
                        <div class="invalid-feedback">Logo image is requeued</div>
                    </div>
                </div>
                <hr/>
                <h4>Select manager</h4>
                <div>
                    <div class="form-group">
                        <label>Manager</label>
                        <select class="form-control" v-model="manager" v-bind:class="{'is-invalid' : !manager && submitted}" required>
                            <option v-for="item in managers" v-on:click="selectChange(item)">{{getManagerString(item)}}</option>
                        </select>
                        <div class="invalid-feedback">Manager not selected</div>
                    </div>
                    <button class="btn btn-primary" data-toggle="modal" data-target="#registerManagerModal">Register new manager</button>
                    <register-manager id="registerManagerModal" v-on:newManager="selectManger"></register-manager>
                </div>

                <hr/>
                <h4>Working hours</h4>
                <div style="width: 50%;">
                    <div v-for="item in workingHours">
                        <div class="wh-container">
                            <h5 style="align-self: center;">{{item.day}}</h5>
                            <div class="wh-select-container" style="align-items: center; justify-content: center;"> 
                                <h6>From</h6>
                                <select class="form-control" v-model="item.fromH">
                                    <option v-for="h in hours">{{h}}</option>
                                </select>
                                <h5>:</h5>
                                <select class="form-control" v-model="item.fromM">
                                    <option v-for="m in minutes">{{m}}</option>
                                </select>
                            </div>
                            <div class="wh-select-container" style="align-items: center; justify-content: flex-end;"> 
                                <h6>To</h6>
                                <select class="form-control" v-model="item.toH">
                                    <option v-for="h in hours">{{h}}</option>
                                </select>
                                <h5>:</h5>
                                <select class="form-control" v-model="item.toM">
                                    <option v-for="m in minutes">{{m}}</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-dark button-submit" v-on:click="createNewFacility">Create</button>
            </form>
        </div>
        <div v-else>
            <div class="alert alert-success" role="alert">
                <h5>Successful created</h5>
                <p>New facility created.</p>
                </br>
                <a href="#/" >Home</a>
            </div>
        </div>
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
        this.lon = item.lon;
        this.lat = item.lat;
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
    },
    validName: function() {
        if(!this.name) {
            return false;
        } else if(this.name.trim() === '') {
            return false;
        }
        return true;

    },
    validateForm: function() {
        if(this.validName() && this.type && this.filePreview && this.streetName && this.city && this.manager ) {
            return true;
        }
        return false;
    },
    createNewFacility: function(e) {
        e.preventDefault();

        this.workingHours.forEach(element => {
            if(element.fromH === '--' || element.fromM === '--'){
                element.fromH = '--';
                element.fromM = '--';
                element.toH = '--';
                element.toM = '--';
            } else if(element.toH === '--' || element.toM === '--'){
                element.toH = '--';
                element.toM = '--';
                element.fromH = '--';
                element.fromM = '--';
            }
        })
        this.submitted = true;
        if(this.validateForm()){
            console.log('valid');
            var wh = [];
            this.workingHours.forEach(element => {
                wh.push({
                    day: element.day,
                    from: element.fromH + ":" + element.fromM,
                    to: element.toH + ":" + element.toM
                })
            });
            var t = this.type.replace(' ', '_');
            var facilityDTO = {
                name: this.name,
                facilityType: t,
                location: {
                    longitude: this.lon,
                    latitude: this.lat,
                    address: {
                        streetName: this.streetName,
                        number: this.streetNumber,
                        city: this.city,
                        postalCode: this.postalCode
                    }
                },
                managerId: this.selectedManager.id,
                workingHours: wh
            }
            axios.post('/facilities/new', JSON.stringify(facilityDTO))
            .then(res => {
                let data = new FormData();
                data.append('file', this.$refs.fileInput.files[0])
                let config = {
                    header : {
                        "Content-Type" : "multipart/form-data"
                    }
                }

                axios.post('/facilities/'+res.data.id+'/logo', data, config)
                .then(res => {
                    console.log("uploaded");
                    this.success = true;
                })
            })
        }
    },

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