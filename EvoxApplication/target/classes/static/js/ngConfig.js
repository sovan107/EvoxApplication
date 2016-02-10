EvoxApp.config(function($routeProvider, $httpProvider, $stateProvider) {

	// Setup the route providers
	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home'
	}).when('/login', {
		templateUrl : 'login.html',
		controller : 'navigation'
	}).when('/main', {
		templateUrl : 'main.html',
		controller : 'main'
	}).when('/signUp', {
		templateUrl : 'signup.html',
		controller : 'signup'
	}).otherwise('/');

	$stateProvider.state('home', {
	    url: '/',
	    controller: 'home',
	    templateUrl: 'home.html',
	    data: {
	      requiresLogin: true
	    }
	 });
	
	$stateProvider.state('login', {
	    url: '/login',
	    controller: 'navigation',
	    templateUrl: 'login.html'
	  });
	
	$stateProvider.state('signup', {
	    url: '/signUp',
	    controller: 'signup',
	    templateUrl: 'signup.html'
	  });
	
	// This is added to let the client know that this is not browser request 
	//but treat it as XML request
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).run(['$rootScope', '$location', function($rootScope, $location) {

	  // register listener to watch route changes
	  $rootScope.$on("$routeChangeStart", function(event, next, current) {

	      //TODO : Check if the jwt is set or not, if not set, redirect user to login page 

	  });
}]);