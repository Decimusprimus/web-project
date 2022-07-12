Vue.component("facility", {
data: function() {
    return {
        facility: {
            facilityType: '',
            location: {
                latitude: '',
                longitude: '',
                address: {
                    streetName: '',
                    number: '',
                    city: '',
                    postalCode: '',
                }
            }
        },
        image: '',
        isOpen: true,
        isManagerFacility: false,
        trainings: [],
    }
},
template: `
    <div class="container" style="margin-bottom: 150px;">
        <div class="facility-header">
            <img class="facility-header-img" v-bind:src="image" >
            <div class="container" style="align-self: center">
                <h3>{{facility.name}}</h3>
                <h5>Score: <span class="badge badge-primary">4.5</span></h5>
            </div>
            <div style="align-self: center">
                <button class="btn btn-outline-success btn-lg" v-if="isOpen" disabled>Open</button>
                <button class="btn btn-outline-danger btn-lg" v-else disabled>Closed</button>
            </div>
        </div>
        <div class="facility-type">
            <div class="facility-type-text">{{getType()}}</div>
        </div>

        <div class="facility-content">
            <div class="container" style="width=20%">
                <h4>Location</h4>
                <hr/>
                <h6 class="f-bold">{{facility.location.address.streetName}}  {{facility.location.address.number}}</h6>
                <h6>{{facility.location.address.city}} {{facility.location.address.postalCode}}</h6>
                <h6 class="f-light">{{facility.location.latitude}}, {{facility.location.longitude}}</h6>
            </div>
            <div class="container">
                <h4>Working hours</h4>
                <hr/>
                <div class="f-wh" v-for="item in facility.workingHours">
                    <h6>{{item.day}}</h6>
                    <h6>{{item.from}} - {{item.to}}</h6>
                </div>
            </div>
        </div>
        <div class="container" style="margin-top: 20px;">
            <div class="flex-content-row" style="justify-content: space-between;">
                <h4>Content</h4>
                <button class="btn btn-primary" data-toggle="modal" data-target="#facilityContentModal" v-if="isManagerFacility">Add new content</button>
                <facility-content-modal id="facilityContentModal" v-if="isManagerFacility" v-on:newTraining="newTraining"></facility-content-modal>
            </div>
            <hr/>
            <facility-content-card v-for="item in trainings" v-bind:content="item" v-bind:isMy="isManagerFacility" v-bind:key="item.id"></facility-content-card>
        </div>
        <div class="container" style="margin-top: 50px;">
            <h4>Comments</h4>
            <hr/>
        </div>
    </div>
`,
methods: {
    checkIsOpen: function() {
        var today = new Date(); 
        var i = today.getDay()-1;
        if(i == -1) {
            i = 6;
        }
        var workHour = this.facility.workingHours[i];
        var from = workHour.from.split(":");
        var to = workHour.to.split(":");
        if(from[0] === '--' || from[1] === '--' || to[0] === '--' || to[1] === '--') {
            this.isOpen = false;
            return
        }
        var fromDate = new Date();
        fromDate.setHours(parseInt(from[0]), parseInt(from[1]));
        var toDate = new Date();
        toDate.setHours(parseInt(to[0]), parseInt(to[1]));
        if(fromDate.getTime() === toDate.getTime()) {
            this.isOpen = true;
        } else if(fromDate.getTime() <= today.getTime() && toDate.getTime() >= today.getTime() ) {
            this.isOpen = true;
        } else {
            this.isOpen = false;
        }
    },
    getType: function() {
        var ret = this.facility.facilityType.replace("_", " ");
        return ret;
    },
    checkManager: function(id) {
        var userId = window.localStorage.getItem('user');
        if(userId === id) {
            this.isManagerFacility = true;
        } else {
            this.isManagerFacility = false;
        }
    },
    newTraining: function(t) {
        this.trainings.push(t);
        console.log(this.trainings);
    },
},
mounted() {
    const id = this.$route.params.id;
    if(id) {
        this.image = '/facilities/'+id+'/logo';
        axios.get('/facilities/'+id)
        .then(res =>{
            this.facility = res.data;
            this.checkIsOpen();
            this.checkManager(res.data.managerId)
        })

        axios.get('/training/facility/'+id)
        .then(res => {
            this.trainings = res.data;
        })
    }
},

});