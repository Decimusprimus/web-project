const Home = { template: '<home-component></home-component>' }
const Login = { template: '<login></login>' }
const RegisterCustomer = { template: '<register-customer></register-customer>' }
const FacilityCard = { template: '<facility-card></facility-card>' }
const Facilities = { template: '<facilities></facilities>' }
const Profile = { template: '<profile></profile>' }
const FacilityNew = { template: '<facility-new></facility-new>' }
const Facility = { template: '<facility></facility>' }
const RegisterCoach = { template: '<register-coach></register-coach>' } 
const ManagerFacility = { template: '<manager-facility></manager-facility>'}

const router = new VueRouter({
	mode: 'hash',
	routes: [
		{ path: '/', component: Home },
		{ path: '/login', component: Login },
		{ path: '/register', component: RegisterCustomer },
		{ path: '/profile', component: Profile },
		{ path: '/facility/new', component: FacilityNew },
		{ path: '/facility/:id', component: Facility },
		{ path: '/coach/register', component: RegisterCoach },
		{ path: '/manager/facility', component: ManagerFacility },
	]
})

var app = new Vue({
	router,
	el: '#app',
	data: {
		isLoggedIn : false,
		user : String,
		userRole : '',
		username : '',
		isAdmin: false,
		isManager: false,
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
				this.username = window.localStorage.getItem('username');
				this.userRole = window.localStorage.getItem('userRole');
				if(this.userRole === 'ADMIN') {
					this.isAdmin = true;
					this.isManager = false;
				} else if(this.userRole === 'MANAGER') {
					this.isAdmin = false;
					this.isManager = true;
				} else {
					this.isAdmin = false;
					this.isManager = false;
				}
				 
				var userId = {
					id: this.user
				}
				axios.post('/user/relogin', JSON.stringify(userId))
				.then(res => {
					this.isLoggedIn = true;
				})
				.catch(err => {
					this.isLoggedIn  = false;
					window.localStorage.removeItem('user');
					window.localStorage.removeItem('userRole');
					window.localStorage.removeItem('username');
				})
			}
		},

		logout: function() {
			axios.post('/user/logout')
			.then(res => {
				window.localStorage.removeItem('user');
				window.localStorage.removeItem('userRole');
				window.localStorage.removeItem('username');
				this.isLoggedIn = false;
				this.isAdmin = false;
				this.isManager = false;
				this.$router.push('/');
			})
		},

		goToProfile: function() {
			this.$router.push('/profile');
		}
	}
})