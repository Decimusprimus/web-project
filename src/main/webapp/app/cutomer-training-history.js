Vue.component("customer-training-history", {
    data: function() {
    return {
        trainings: []
    }
},
template: `
    <div class="container" style="margin-top:50px;">
        <customer-training-card  v-for="item in trainings" v-bind:content="item" v-bind:key="item.id"></customer-training-card>
    </div>
`,
methods: {
    
},
mounted() {
    axios.get('/training/customer/history')
    .then(res => {
        this.trainings = res.data;
    })
},
});