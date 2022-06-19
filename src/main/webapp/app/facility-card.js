Vue.component("facility-card", {
    props: ['facility'],
    data: function() {
    return {
        a : null,
        image: '',
    }
    },
    template: `
        <div class="card facility-card">
            <img class="card-img-top facility-img" v-bind:src="image">
            <div class="card-body">
                <div>
                    <h5 class="card-title">{{facility.name}}<span class="badge badge-primary">4.5</span></h5>
                    <h4>{{getAddress()}}</h4>
                    <h4>{{facility.facilityType}}</h4>
                    <h4>Working hours</h4>
                    <ul>
                        <li v-for="item in facility.workingHours">{{item.day}} &emsp; {{item.from}}-{{item.to}}</li>
                    </ul>
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
        }
        
    },
    mounted() {
        this.image = '/facilities/'+this.facility.id+'/logo';
    },
    });