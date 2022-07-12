Vue.component("coach-training-card", {
    data: function() {
    return {
        image: '',
        coach: [],
        facility: [],
    }
},
props: ['content'],
template: `
    <div>
        <div class="f-cnt-container">
            <img v-bind:src="image">
            <div class="f-cnt-grid" style="width: 100%; justify-content: center; align-content: center; margin-right: 20px; margin-left: 20px">
                <h6>{{content.name}}</h6>
                <h6>{{facility.name}}</h6>
            </div>
            <div class="f-cnt-price" >
                <h6 v-if="content.price">{{content.price}}</h6>
            </div>
            <div class="f-cnt-price"  style="margin-left: 20px;">
                <button class="btn btn-primary" v-if="isManager" style="height: 40px;" data-toggle="modal" data-target="#facilityContentEdit">Edit</button>
            </div>
        </div>
        <facility-content-edit id="facilityContentEdit" v-bind:content="content" v-bind:key="content.id"></facility-content-edit>
        <hr/>
    </div>
    
`,
methods: {
},
mounted() {
    this.image = '/training/'+this.content.id+'/image';
    if(this.content.coachId){
        axios.get('/user/'+this.content.coachId)
        .then(res => {
                this.coach = res.data;
        }) 
    }
    axios.get('/facilities/'+this.content.facilityId)
    .then(res => {
        this.facility = res.data;
    }
        
    )
},
});