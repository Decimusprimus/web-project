Vue.component("membership", {
    data: function() {
    return {
        option: 0,
        success: false,
    }
},
template: `
    <div class="container" style="margin-bottom: 150px;">
        <div v-if="!success">
            <div class="m-header container">
                <h2>Buy membership</h2>
            </div>
            <div class="m-container">
                <div class="m-card-cnt m-card">
                    <div class="m-card-header">
                        <h3>Basic</h3>
                    </div>
                    
                    <div class="m-card-header">
                        <h3>12</h3>
                        <p>Credits per month</p>
                    </div>
                    <h5>1 entry per day</h5>
                    <div class="m-card-header">
                        <h4>For 1 month</h4>
                    </div>
                    <div class="m-purchase">
                        <h4>2600 RSD</h4>
                        <button class="btn btn-dark" v-on:click="option1">Purchase</button>
                    </div>
                </div>
                <div class="m-card-cnt m-card">
                    <div class="m-card-header">
                        <h3>Classic</h3>
                    </div>
                    
                    <div class="m-card-header">
                        <h3>16</h3>
                        <p>Credits per month</p>
                    </div>
                    <h5>1 entry per day</h5>
                    <div class="m-card-header">
                        <h4>For 1 month</h4>
                    </div>
                    <div class="m-purchase">
                        <h4>3000 RSD</h4>
                        <button class="btn btn-dark" v-on:click="option2">Purchase</button>
                    </div>
                </div>
                <div class="m-card-cnt m-card">
                    <div class="m-card-header">
                        <h3>Elite</h3>
                    </div>
                    
                    <div class="m-card-header">
                        <h3>30</h3>
                        <p>Credits per month</p>
                    </div>
                    <h5>1 entry per day</h5>
                    <div class="m-card-header">
                        <h4>For 1 month</h4>
                    </div>
                    <div class="m-purchase">
                        <h4>4400 RSD</h4>
                        <button class="btn btn-dark" v-on:click="option3">Purchase</button>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="m-container">
                <div class="m-card-cnt m-card-special">
                    <div class="m-card-header">
                        <h3>Basic</h3>
                    </div>
                    
                    <div class="m-card-header">
                        <h3>12</h3>
                        <p>Credits per month</p>
                    </div>
                    <h5>1 entry per day</h5>
                    <div class="m-card-header">
                        <h4>For 3 month</h4>
                    </div>
                    <div class="m-purchase">
                        <h4>7000 RSD</h4>
                        <button class="btn btn-dark" v-on:click="option4">Purchase</button>
                    </div>
                </div>
                <div class="m-card-cnt m-card-special">
                    <div class="m-card-header">
                        <h3>Classic</h3>
                    </div>
                    
                    <div class="m-card-header">
                        <h3>16</h3>
                        <p>Credits per month</p>
                    </div>
                    <h5>1 entry per day</h5>
                    <div class="m-card-header">
                        <h4>For 3 month</h4>
                    </div>
                    <div class="m-purchase">
                        <h4>8000 RSD</h4>
                        <button class="btn btn-dark" v-on:click="option5">Purchase</button>
                    </div>
                </div>
                <div class="m-card-cnt m-card-special">
                    <div class="m-card-header">
                        <h3>Elite</h3>
                    </div>
                    
                    <div class="m-card-header">
                        <h3>30</h3>
                        <p>Credits per month</p>
                    </div>
                    <h5>1 entry per day</h5>
                    <div class="m-card-header">
                        <h4>For a year</h4>
                    </div>
                    <div class="m-purchase">
                        <h4>15000 RSD</h4>
                        <button class="btn btn-dark" v-on:click="option6">Purchase</button>
                    </div>
                </div>
            </div>
        </div>
        <div v-else>
            <div class="alert alert-success" role="alert" style="margin-top:50pxl;">
                <h5>Membership purchased</h5>
                <p>You successfully purchased a membership</p>
                </br>
                <a href="#/" >Home</a>
            </div>
        </div>
    </div>
`,
methods: {
    option1: function() {
        this.option = 1; 
        this.buyMembership();
    },
    option2: function() {
        this.option = 2; 
        this.buyMembership();
    },
    option3: function() {
        this.option = 3; 
        this.buyMembership();
    },
    option4: function() {
        this.option = 4; 
        this.buyMembership();
    },
    option5: function() {
        this.option = 5; 
        this.buyMembership();
    },
    option6: function() {
        this.option = 6; 
        this.buyMembership();
    },
    buyMembership: function() {
        axios.post('/membership/'+this.option)
        .then(res => {
            console.log(res.data);
            this.success = true;
        })
    }
},
mounted() {
    
},
});