Vue.component("coach-training", {
    data: function() {
    return {
        trainings: []
    }
},
template: `
    <div class="container" style="margin-top:50px;">
        <coach-training-card  v-for="item in trainings" v-bind:content="item" v-bind:key="item.id"></coach-training-card>
    </div>
`,
methods: {
    
},
mounted() {
    axios.get('/training/coach/my')
    .then(res => {
        this.trainings = res.data;
    })
},
});