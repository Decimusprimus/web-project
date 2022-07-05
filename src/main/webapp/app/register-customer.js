Vue.component("register-customer", {
    data: function() {
        return {
            username: '',
            password: '',
            confirmPassword: '',
            firstName: '',
            lastName: '',
            dateOfBirth: '',
            gender: '',
        }
    },
    template: `
    <div class="container" style="margin-top: 50px; width: 30%">
        <h2>Create an account</h2>
        <form  style="margin-top: 50px;">
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
                    <option>Male</option>
                    <option>Female</option>
                    <option>Other</option>
                </select>
            </div>
            <div class="form-group">
                <label for="dateOfBirth">Date of birth</label>
                <input type="date" class="form-control" id="dateOfBirth" v-model="dateOfBirth">
            </div>
            <button type="submit" class="btn btn-dark" v-on:click="register" style="width: 100%; margin-top: 30px">Register</button>
        </form>
    </div>
    `,
    mounted() {

    },
    methods: {
        register: function() {
            console.log(this.gender);
            console.log(this.dateOfBirth);
            var registerDTO = {
                username: this.username,
                password: this.password,
                passwordConfirm: this.confirmPassword,
                firstName: this.firstName,
                lastName: this.lastName,
                dateOfBirth: this.dateOfBirth,
                gender: this.gender.toUpperCase()
            }

            axios.post('/user/register/customer', JSON.stringify(registerDTO))
            .then(res => {
                console.log(res.data);
            })

        },
    }
});