const Home = { template: '<home-component></home-component>'}

const router = new VueRouter({
	mode: 'hash',
	routes: [
		{ path: '/', component: Home }
	]
})

var app = new Vue({
	router,
	el: '#app'
})