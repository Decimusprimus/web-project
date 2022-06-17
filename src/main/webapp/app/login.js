Vue.component("login", {
    data: function() {
        return {
            username: '',
            password: '',
            error: false,
        }
    },
    template: `
    <div class="container" style="margin-top: 50px; width: 30%">
        <div v-if="error" class="error-box"> 
            <h5 class="error-text">Incorrect username or password.</h5> 
        </div>
        <form>
            <div class="form-group">
                <label for="usernameLogin">Username</label>
                <input type="email" class="form-control" id="usernameLogin" v-model="username" >
            </div>
            <div class="form-group">
                <label for="passwordLogin">Password</label>
                <input type="password" class="form-control" id="passwordLogin" v-model="password">
            </div>
            <button type="submit" class="btn btn-dark" v-on:click="login" style="width: 100%; margin-top: 20px">Login</button>
        </form>
    </div>
    `,
    mounted() {

    },
    methods: {
        login: function() {
            var loginDTO = {
                username: this.username,
                password: this.password,
            }
            axios.post('/user/login', JSON.stringify(loginDTO))
            .then(res => {
                window.localStorage.setItem('user', res.data.id);
                this.$root.checkLogin();
                this.$router.push('/');
            })
            .catch(err => {
                this.error = true;
                this.borderColor = 'red';
            })
        }
    }
})
