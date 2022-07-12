Vue.component("facility-content-card", {
    data: function() {
    return {
        image: '',
        coach: [],
        isManager: false,
        isCustomer: false,
        canCheckIn: false,
    }
},
props: ['content', 'isMy'],
template: `
    <div>
        <div class="f-cnt-container">
            <img v-bind:src="image">
            <div class="f-cnt-grid" style="width: 100%; justify-content: center; align-content: center; margin-right: 20px; margin-left: 20px">
                <h6>{{content.name}}</h6>
                <h6>{{content.description}}</h6>
                <h6>{{coach.firstName}} {{coach.lastName}}</h6>
            </div>
            <div class="f-cnt-price" >
                <h6 v-if="content.price">{{content.price}}</h6>
            </div>
            <div class="f-cnt-price"  style="margin-left: 20px;">
                <button class="btn btn-primary" v-if="isManager" style="height: 40px;" data-toggle="modal" data-target="#facilityContentEdit">Edit</button>
                <button class="btn btn-primary" v-if="isCustomer" v-bind:disabled="!canCheckIn" v-on:click="checkIn">Check in</button>
            </div>
        </div>
        <facility-content-edit id="facilityContentEdit" v-bind:content="content" v-bind:key="content.id"></facility-content-edit>
        <hr/>
    </div>
    
`,
methods: {
    checkUserRole: function() {
        var role = window.localStorage.getItem('userRole');
        var id = window.localStorage.getItem('user');
        if(role){
            if(role === 'MANAGER' && this.isMy){
                this.isManager = true;
                this.isCustomer = false;
            } else if(role === 'CUSTOMER') {
                this.isCustomer = true;
                this.isManager = false;
            }
        }
    },
    checkIn: function() {
        var dto = {
            trainingId: this.content.id,
            coachId: this.content.coachId,
        }
        axios.post('/training/checkin', JSON.stringify(dto))
        .then(res => {
            window.alert('Checked in :   ^)');
        })
    }
},
mounted() {
    this.image = '/training/'+this.content.id+'/image';
    this.checkUserRole();
    if(this.content.coachId){
        axios.get('/user/'+this.content.coachId)
        .then(res => {
                this.coach = res.data;

        }) 
    }
    if(this.isCustomer) {
        axios.get('/training/can/checkin')
        .then(res => {
            this.canCheckIn = true
        })
    }
},
});