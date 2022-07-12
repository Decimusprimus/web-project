Vue.component("manager-facility", {
    data: function() {
    return {
        hasFacility: true,
    }
},
template: `
    <div v-if="!hasFacility" class="container" style="margin-top:50px;">
        <div class="alert alert-warning" role="alert">
            <h5>You have not been assign to a facility</h5>
            </br>
            <a href="/" >Home</a>
        </div>
    </div>
`,
methods: {
    
},
mounted() {
    axios.get('/facilities/manager/my')
    .then(res => {
        this.$router.push('/facility/'+ res.data.id);
        this.hasFacility = true;
    })
    .catch(err => {
        this.hasFacility = false;
    })
    
},
});