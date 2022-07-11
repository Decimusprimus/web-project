Vue.component("register-coach", {
    data: function() {
        return {
            username: '',
            password: '',
            confirmPassword: '',
            firstName: '',
            lastName: '',
            dateOfBirth: '',
            gender: '',
            submitted: false,
            usernameErrorMsg: '',
            passwordErrorMsg: '',
            confirmPasswordErrorMsg: '',
            firstNameErrorMsg: '',
            lastNameErrorMsg: '',
            dateOfBirthErrorMsg: '',
            genderErrorMsg: '',
            validUsernameResponse: true,
            success: false
        }
    },
    template: `
    <div class="container" style="margin-top: 50px; width: 40%">
        <div v-if="!success">
            <h2>Register new coach</h2>
            <form  style="margin-top: 50px; margin-bottom: 100px">
                <div class="form-group">
                    <label for="usernameRegister">Username</label>
                    <input type="email" class="form-control" id="usernameRegister" v-model="username" v-on:change="usernameChange" v-bind:class="{'is-invalid' : !validUsername() && submitted}">
                    <div class="invalid-feedback">{{usernameErrorMsg}}</div>
                </div>
                <div class="form-group">
                    <label for="passwordRegister">Password</label>
                    <input type="password" class="form-control" id="passwordRegister" v-model="password" v-bind:class="{'is-invalid' : !validPassword() && submitted}">
                    <div class="invalid-feedback">{{passwordErrorMsg}}</div>
                </div>
                <div class="form-group">
                    <label for="confirmPasswordRegister">Confirm password</label>
                    <input type="password" class="form-control" id="confirmPasswordRegister" v-model="confirmPassword" v-bind:class="{'is-invalid' : !validConfirmPassword() && submitted}">
                    <div class="invalid-feedback">{{confirmPasswordErrorMsg}}</div>
                </div>
                <div class="form-group">
                    <label for="firstNameRegister">Fist name</label>
                    <input type="email" class="form-control" id="firstNameRegister" v-model="firstName" v-bind:class="{'is-invalid' : !validFirstName() && submitted}">
                    <div class="invalid-feedback">{{firstNameErrorMsg}}</div>
                </div>
                <div class="form-group">
                    <label for="lastNameRegister">Last name</label>
                    <input type="email" class="form-control" id="lastNameRegister" v-model="lastName" v-bind:class="{'is-invalid' : !validLastName() && submitted}">
                    <div class="invalid-feedback">{{lastNameErrorMsg}}</div>
                </div>
                <div class="form-group">
                    <label for="genderSelect">Gender</label>
                    <select class="form-control" v-model="gender" v-bind:class="{'is-invalid' : !validGender() && submitted}">
                        <option>Male</option>
                        <option>Female</option>
                        <option>Other</option>
                    </select>
                    <div class="invalid-feedback">{{genderErrorMsg}}</div>
                </div>
                <div class="form-group">
                    <label for="dateOfBirth">Date of birth</label>
                    <input type="date" class="form-control" id="dateOfBirth" v-model="dateOfBirth" v-bind:class="{'is-invalid' : !validDateOfBirth() && submitted}">
                    <div class="invalid-feedback">{{dateOfBirthErrorMsg}}</div>
                </div>
                <button type="submit" class="btn btn-dark" v-on:click="register" style="width: 100%; margin-top: 30px">Register</button>
            </form>
        </div>
        <div v-else>
            <div class="alert alert-success" role="alert">
                <h5>Successful registration</h5>
                <p>New coach successfully registered</p>
                </br>
                <a href="#/" >Home</a>
            </div>
        </div>
    </div>
    `,
    mounted() {

    },
    methods: {
        usernameChange: function() {
            if(!this.validUsernameResponse){
                this.validUsernameResponse = true;
            }
        },
        validUsername: function() {
            if(!this.username){
                this.usernameErrorMsg = 'Username is requeued'
                return false;
            } else if(this.username.length < 4) {
                this.usernameErrorMsg = 'Username must be at least 4 characters long'
                return false;
            } else if(!this.validUsernameResponse) {
                this.usernameErrorMsg = 'Username already taken'
                return false;
            }
            this.usernameErrorMsg = '';
            return true;
        },
        validPassword: function() {
            if(!this.password){
                this.passwordErrorMsg = 'Password is requeued'
                return false;
            } else if(this.password.length < 4) {
                this.passwordErrorMsg  = 'Password must be at least 4 characters long'
                return false;
            } else if(this.confirmPassword !== this.password) {
                this.passwordErrorMsg = 'Passwords don\'t mach';
                return false;
            }
            this.passwordErrorMsg = '';
            return true;
        },
        validConfirmPassword: function() {
            if(!this.confirmPassword){
                this.confirmPasswordErrorMsg = 'Confirm password is requeued';
                return false;
            } else if(this.confirmPassword !== this.password) {
                this.confirmPasswordErrorMsg = 'Passwords don\'t mach'
                return false;
            }
            this.confirmPasswordErrorMsg = '';
            return true;
        },
        validFirstName: function() {
            if(!this.firstName) {
                this.firstNameErrorMsg = 'First name is requeued'
                return false;
            } 
            this.firstNameErrorMsg = '';
            return true;
        },
        validLastName: function() {
            if(!this.lastName) {
                this.lastNameErrorMsg = 'Last name is requeued';
                return false;
            }
            this.lastNameErrorMsg = '';
            return true;
        },
        validGender: function() {
            if(!this.gender) {
                this.genderErrorMsg = 'Gender is requeued'
                return false;
            }
            return true;
        },
        validDateOfBirth: function() {
            if(!this.dateOfBirth) {
                this.dateOfBirthErrorMsg = 'Date of birth is requeued'
                return false;
            } 
            const date = new Date(this.dateOfBirth);
            const today = new Date();
            if(date > today ) {
                this.dateOfBirthErrorMsg = 'Are you from future?'
                return false
            }
            this.dateOfBirthErrorMsg = '';
            return true;
        },
        validateForm: function() {
            if(this.validUsername() && this.validPassword() && this.validConfirmPassword() && this.validGender() && this.validGender() && this.validDateOfBirth()){
                return true;
            }
            return false;
        },
        register: function(e) {
            e.preventDefault();
            this.submitted = true;
            if(this.validateForm()) {
                var registerDTO = {
                    username: this.username,
                    password: this.password,
                    passwordConfirm: this.confirmPassword,
                    firstName: this.firstName,
                    lastName: this.lastName,
                    dateOfBirth: this.dateOfBirth,
                    gender: this.gender.toUpperCase()
                }
                
                axios.post('/user/register/coach', JSON.stringify(registerDTO))
                .then(res => {
                    console.log(res.data);
                    this.success = true;
                })
                .catch(err => {
                    if(err.response.data === 'Username already taken') {
                        this.validUsernameResponse = false;
                    }
                })
            }
        },
    }
});