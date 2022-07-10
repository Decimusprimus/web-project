Vue.component("register-manager", {
    data: function() {
    return {
        username: '',
        password: '',
        confirmPassword: '',
        firstName: '',
        lastName: '',
        dateOfBirth: '',
        gender: '',
        showModal: true,
    }
},
template: `
    <div class="modal fade" tabindex="-1" aria-labelledby="modalLabel" id="mangerModal" ref="mModal" aria-hidden="true" v-if="showModal">
        <div class="modal-dialog ">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalLabel">Register new manager</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" ref="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="usernameRegister">Username</label>
                            <input type="email" class="form-control" id="usernameRegister" v-model="username" >
                        </div>
                        <div class="form-group">
                            <label for="passwordRegister">Password</label>
                            <input type="password" class="form-control" id="passwordRegister" v-model="password">
                        </div>
                        <div class="form-group">
                            <label for="confirmPasswordRegister">Confirm password</label>
                            <input type="password" class="form-control" id="confirmPasswordRegister" v-model="confirmPassword">
                        </div>
                        <div class="form-group">
                            <label for="firstNameRegister">Fist name</label>
                            <input type="email" class="form-control" id="firstNameRegister" v-model="firstName" >
                        </div>
                        <div class="form-group">
                            <label for="lastNameRegister">Last name</label>
                            <input type="email" class="form-control" id="lastNameRegister" v-model="lastName" >
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
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" v-on:click="register">Save changes</button>
                </div>
            </div>
        </div>
    </div>
`,
methods: {
    register: function(e) {
       
        var registerDTO = {
            username: this.username,
            password: this.password,
            passwordConfirm: this.confirmPassword,
            firstName: this.firstName,
            lastName: this.lastName,
            dateOfBirth: this.dateOfBirth,
            gender: this.gender
        }
        console.log(registerDTO);
        axios.post('/user/register/manager', JSON.stringify(registerDTO))
        .then(res => {
            console.log("Manager registered");
            this.$emit('newManager', res.data);
            this.$refs.Close.click();
        })
        
    },
},
mounted() {
    
},
});