Vue.component("facility-card", {
    props: ['facility'],
    data: function() {
    return {
        a : null,
        image: '',
        isOpen: true,
    }
    },
    template: `
        <div class="card facility-card">
            <img class="card-img-top facility-img" v-bind:src="image" v-on:click="goToFacility">
            <div class="card-body">
                <div>
                    <h4 class="card-title">{{facility.name}} &emsp; <span class="badge badge-primary" v-if="facility.averageScore !== 0">{{facility.averageScore}}</span></h4>
                    <h5>{{getAddress()}}</h5>
                    <h6>{{facility.facilityType}}</h6>
                    <div>
                        <button class="btn btn-outline-success" v-if="isOpen">Open</button>
                        <button class="btn btn-outline-danger" v-else>Closed</button>
                        <div class="btn-group dropup">
                            <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Working hours</button>
                            <div class="dropdown-menu">
                                <div class="dropdown-item work-hour " v-for="item in facility.workingHours">
                                    <a>{{item.day}}</a>
                                    <span>&emsp;</span>
                                    <a>{{item.from}} - {{item.to}}</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `,
    methods: {
        getAddress: function() {
            return this.facility.location.address.streetName + ' ' + this.facility.location.address.number + ' ,' + this.facility.location.address.city + ' ' + this.facility.location.address.postalCode;
        },
        getImage: function() {
            return '/facilities/'+this.facility.id+'/logo';
        },
        checkIsOpen: function() {
            var today = new Date(); 
            var i = today.getDay()-1;
            if(i == -1) {
                i = 6;
            }
            var workHour = this.facility.workingHours[i];
            var from = workHour.from.split(":");
            var to = workHour.to.split(":");
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
        goToFacility: function() {
            const id = this.facility.id;
            this.$router.push('/facility/'+ id);
        }
        
    },
    mounted() {
        this.image = '/facilities/'+this.facility.id+'/logo';
        this.facility.facilityType = this.facility.facilityType.replaceAll('_', ' ');
        this.checkIsOpen();
    },
    });