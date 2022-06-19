const Home = { template: '<home-component></home-component>' }
const Login = { template: '<login></login>' }
const RegisterCustomer = { template: '<register-customer></register-customer>' }
const FacilityCard = { template: '<facility-card></facility-card>' }
const Facilities = { template: '<facilities></facilities>' }

const router = new VueRouter({
	mode: 'hash',
	routes: [
		{ path: '/', component: Home },
		{ path: '/login', component: Login },
		{ path: '/register', component: RegisterCustomer }
	]
})

var app = new Vue({
	router,
	el: '#app',
	data: {
		isLoggedIn : false,
		user : String,
	},

	mounted() {
		this.checkLogin();
	},
	methods: {
		checkLogin: function() {
			if(!window.localStorage.getItem('user'))
			{
				this.isLoggedIn = false;
			} else {
				this.isLoggedIn = true;
				this.user = window.localStorage.getItem('user');
			}
		},

		logout: function() {
			axios.post('/user/logout')
			.then(res => {
				window.localStorage.removeItem('user');
				this.isLoggedIn = false;
				this.$router.go(0);
			})
		}
	}
})