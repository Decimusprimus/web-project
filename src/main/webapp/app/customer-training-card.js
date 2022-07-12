Vue.component("customer-training-card", {
    data: function() {
    return {
        image: '',
        coach: [],
        facility: [],
        training: [],
    }
},
props: ['content'],
template: `
    <div>
        <div class="f-cnt-container">
            <img v-bind:src="image">
            <div class="f-cnt-grid" style="width: 100%; justify-content: center; align-content: center; margin-right: 20px; margin-left: 20px">
                <h6>{{training.name}}</h6>
                <h6>{{facility.name}}</h6>
            </div>
            <div class="f-cnt-price" style="margin-right: 20px;">
                <h6 v-if="training.price">{{training.price}}</h6>
            </div>
            <div class="f-cnt-price" >
                <h6>Date: {{content.dateTime}}</h6>
            </div>
            <div class="f-cnt-price"  style="margin-left: 20px;">
            </div>
        </div>
        <hr/>
    </div>
    
`,
methods: {
},
mounted() {
    this.image = '/training/'+this.content.trainingId+'/image';
    if(this.content.coachId){
        axios.get('/user/'+this.content.coachId)
        .then(res => {
                this.coach = res.data;
        }) 
    }
    axios.get('/facilities/'+this.content.facilityId)
    .then(res => {
        this.facility = res.data;
    })

    axios.get('training/'+this.content.trainingId)
    .then(res => {
        this.training = res.data;
        axios.get('/facilities/'+this.training.facilityId)
        .then(res => {
            this.facility = res.data;
        })
    })
},
});