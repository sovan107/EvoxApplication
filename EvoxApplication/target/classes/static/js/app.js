angular.module('EvoxApplication', [ 'ngRoute','ui.router','angular-storage','angular-jwt'])

.config(function($routeProvider, $httpProvider, $stateProvider) {

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
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).run(['$rootScope', '$location', function($rootScope, $location) {

	  // register listener to watch route changes
	  $rootScope.$on("$routeChangeStart", function(event, next, current) {

	      console.log("Routechanged sessionId=" + $rootScope.SessionId);

	  });
}]).controller(
		'navigation',

		function($rootScope, $scope, $http, $location, $route, store, $state) {

			$scope.credentials = {};
			$scope.tab = function(route) {
				return $route.current && route === $route.current.controller;
			};

			var authenticate = function(credentials, callback) {
				
				$http({
					
					url: 'user',
					method: 'POST',
					data: {
						'username': credentials.username,
						'password': credentials.password
					}
				}).then(function(response){
					
					console.log(response)
					store.set('jwt', response.data);
				    $state.go('home');
				    $rootScope.authenticated = true;
					callback && callback($rootScope.authenticated);
				}, function(error){
					
					$rootScope.authenticated = false;
					callback && callback(false);
					alert(error.data);
				})
			}
			
			
			$scope.login = function() {
				authenticate($scope.credentials, function(authenticated) {
					if (authenticated) {
						
						console.log("Login succeeded");
						$location.path("/main");
						$scope.errorMessage = "";
						$scope.error = false;
						$rootScope.authenticated = true;
						
					} else {
						
						console.log("Login failed")
						$location.path("/login");
						$scope.error = true;
						$scope.errorMessage = "Invalid User Credentails";
						$rootScope.authenticated = false;
					}
				})
			};

			$scope.logout = function() {
				if($scope.authenticated){	
					$http.get('logout', {}).success(function() {
						$rootScope.authenticated = false;
						$location.path("/");
					}).error(function(data) {
						console.log("Logout failed")
						$rootScope.authenticated = false;
					});
				} else {
					$location.path("/login");
				}
			};
			
			$scope.signUp = function() {
				
				console.log('Hello Signing Up');
				$location.path('/signUp');
			};
			
		})
	.controller('home', function($scope, $http, store, jwtHelper) {
			
			$scope.homePageMessage = "This is the home page after login";
			$scope.jwt = store.get('jwt');
			$scope.isSignPage = true;
				
			
		})
	.controller('main', function($scope, $rootScope, $http, store) {
			
		$http({
			url: '/api/resource/',
			method: 'GET',
			headers: {
				'Authorization': 'Bearer '+store.get('jwt')
			}
		}).then(function(response){
			
			$rootScope.SessionId=response.id;
			$scope.greeting = response.content;
			
		},function(error){
			
			alert(error.data);
		})
		
		
		
		
			$http.get('/api/resource/').success(function(data) {
			
			
			})
	})
	.controller('signup', function($scope, $http, store, jwtHelper) {
			
			$scope.signUpDetails = {}
			
			$scope.signUp = function(){
				
				$http({
					url: 'newUserRegistration',
					method: 'POST',
					headers: {
						'Authentication': 'Bearer ' + store.get('jwt')
					},
					data:  $scope.signUpDetails
				}).then(function(response){
					
					console.log(response);
					$scope.error = true;
					$scope.errorMessage = "Success";
					
				},function(error){
					
					alert(error.data);
				})
			}
			
		})
