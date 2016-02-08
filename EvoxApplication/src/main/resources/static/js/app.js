angular.module('EvoxApplication', [ 'ngRoute' ]).config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home'
	}).when('/login', {
		templateUrl : 'login.html',
		controller : 'navigation'
	}).when('/main', {
		templateUrl : 'main.html',
		controller : 'main'
	}).otherwise('/');

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).run(['$rootScope', '$location', function($rootScope, $location) {

	  // register listener to watch route changes
	  $rootScope.$on("$routeChangeStart", function(event, next, current) {

	      console.log("Routechanged sessionId=" + $rootScope.SessionId);

//	      if ($rootScope.SessionId == '' || $rootScope.SessionId == null) {
//
//	          // no logged user, we should be going to #login
//	          if (next.templateUrl == "/login") {
//	              // already going to #login, no redirect needed
//	          } else {
//	              // not going to #login, we should redirect now
//	              $location.path("/login");
//	          }
//	      }
	  });
}]).controller(
		'navigation',

		function($rootScope, $scope, $http, $location, $route) {

			$scope.tab = function(route) {
				return $route.current && route === $route.current.controller;
			};

			var authenticate = function(credentials, callback) {

				$http.post('user', {
					'username': credentials.username,
					'password': credentials.password
				}).success(function (data, status, headers, config) {
					
					console.log("success : " + data);
					
//					if (data.name) {
						
						$rootScope.authenticated = true;
//					} else {
//						
//						$rootScope.authenticated = false;
//					}
					callback && callback($rootScope.authenticated);
				}).error(function (data, status, headers, config) {
					
					console.log("fail : " + data.status);
					
					$rootScope.authenticated = false;
					callback && callback(false);
				});

			}
			
			$scope.credentials = {};
			$scope.login = function() {
				authenticate($scope.credentials, function(authenticated) {
					if (authenticated) {
						
						console.log("Login succeeded")
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
			}

		}).controller('home', function($scope, $http) {
			
			$scope.homePageMessage = "This is the home page before login"
		}).controller('main', function($scope, $rootScope, $http) {
			
			$http.get('/resource/').success(function(data) {
				$rootScope.SessionId=data.id;
				$scope.greeting = data;
		})
});
