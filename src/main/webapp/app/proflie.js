Vue.component("profile", {
    data: function() {
        return {
            id: '',
            username: '',
            usernameCurrent: '',
            oldPassword: '',
            newPassword: '',
            confirmPassword: '',
            firstName: '',
            lastName: '',
            dateOfBirth: '',
            gender: '',
        }
    },
    template: `
    <div class="container">
        <div class="p-container">
            <div class="p-header">
                <h3>{{usernameCurrent}}</h3>
                <hr/>
            </div>
            <div class="p-col">
                <div class="col-md-2 mb-3">
                    <ul class="nav nav-pills flex-column" id="p-tabs" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="p-tabs-profile-tab" data-toggle="tab" href="#p-tabs-profile" role="tab" aria-controls="p-tabs-profile" aria-selected="true">Profile</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="p-tabs-account-tab" data-toggle="tab" href="#p-tabs-account" role="tab" aria-controls="p-tabs-account" aria-selected="false">Account</a>
                        </li>

                    </ul>
                </div>
                <div class="col-md-10">
                    <div class="tab-content" id="p-tabsContent">
                        <div class="tab-pane fade show active" id="p-tabs-profile" role="tabpanel" aria-labelledby="p-tabs-profile-tab">
                            <div  style="width: 60%; margin: auto;">
                                <h2>Profile info</h2>
                                <form  style="margin-top: 50px;">
                                    <div class="form-group">
                                        <label for="firstNameProfile">Fist name</label>
                                        <input type="text" class="form-control" id="firstNameProfile" v-model="firstName" >
                                    </div>
                                    <div class="form-group">
                                        <label for="lastNameProfile">Last name</label>
                                        <input type="text" class="form-control" id="lastNameProfile" v-model="lastName" >
                                    </div>
                                    <div class="form-group">
                                        <label for="genderSelect">Gender</label>
                                        <select class="form-control" v-model="gender">
                                            <option>MALE</option>
                                            <option>FEMALE</option>
                                            <option>OTHER</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="dateOfBirth">Date of birth</label>
                                        <input type="date" class="form-control" id="dateOfBirth" v-model="dateOfBirth">
                                    </div>
                                    <button type="submit" class="btn btn-dark" v-on:click="updateProfile"  style="width: 100%; margin-top: 30px">Update</button>
                                </form>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="p-tabs-account" role="tabpanel" aria-labelledby="p-tabs-account-tab">
                            <div  style="width: 60%; margin: auto;">
                                <h2>Account</h2>
                                <hr/>
                                <div style="margin-top: 30px;">
                                    <h4>Change username</h4>
                                    <form style="margin-top: 30px">
                                        <div class="form-group">
                                            <label for="usernameRegister">Username</label>
                                            <input type="text" class="form-control" id="usernameRegister" v-model="username" >                        
                                        </div>
                                        <button type="submit" class="btn btn-dark" v-on:click="updateUsername" style="width: 100%; margin-top: 30px">Save</button>
                                    </form>
                                </div>
                                <hr/>
                                <div style="margin-top: 30px;">
                                    <h4>Change password</h4>
                                    <form style="margin-top: 30px">
                                        <div class="form-group">
                                            <label for="oldPassword">Old Password</label>
                                            <input type="password" class="form-control" id="oldPassword" v-model="oldPassword" >                        
                                        </div>
                                        <div class="form-group">
                                            <label for="newPassword">New Password</label>
                                            <input type="password" class="form-control" id="newPassword" v-model="newPassword" >                        
                                        </div>
                                        <div class="form-group">
                                            <label for="confirmPassword">Confirm Password</label>
                                            <input type="password" class="form-control" id="confirmPassword" v-model="confirmPassword" >                        
                                        </div>
                                        <button type="submit" class="btn btn-dark" v-on:click="updatePassword" style="width: 100%; margin-top: 30px">Save</button>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>    
    </div>
    `,
    methods: {
        updateProfile: function() {
            var userInfo = {
                firstName: this.firstName,
                lastName: this.lastName,
                dateOfBirth: this.dateOfBirth,
                gender: this.gender
            };
            
            axios.put('/user/'+this.id, JSON.stringify(userInfo))
            .then(res => {
                this.firstName = res.data.firstName;
                this.lastName = res.data.lastName;
                this.dateOfBirth = res.data.dateOfBirth;
                this.gender = res.data.gender;
            })
        },

        updateUsername: function() {
            var username = {
                username: this.username
            }
            axios.post('/user/'+this.id+'/username', this.username)
            .then(res => {
                this.username = res.data.username;
                this.usernameCurrent = res.data.username;
            })
        },

        updatePassword: function() {
            var updatePasswordDTO = {
                oldPassword: this.oldPassword,
                newPassword: this.newPassword,
                confirmPassword: this.confirmPassword
            }
            
            axios.post('/user/'+this.id+'/password', JSON.stringify(updatePasswordDTO))
            .then(res => {
                console.log(res.data);
            })
        }


    },
    mounted() {
        this.username = window.localStorage.getItem('username');
        this.id = window.localStorage.getItem('user');
        axios.get('/user/'+ this.id)
        .then(res => {
            this.username = res.data.username;
            this.usernameCurrent = res.data.username;
            this.firstName = res.data.firstName;
            this.lastName = res.data.lastName;
            this.dateOfBirth = res.data.dateOfBirth;
            this.gender = res.data.gender;
        })
    }
})