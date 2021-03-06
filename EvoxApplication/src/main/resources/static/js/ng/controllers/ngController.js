EvoxApp

	.controller(
		// This handles the loginPage controller
		'login',

		function($rootScope, $scope, $http, $location, $route, store, $state) {
			
			$scope.isCollapsed = true;
			// On load make the login field model blank
			$scope.credentials = {};
			
			$scope.tab = function(route) {
				return $route.current && route === $route.current.controller;
			};
			
			$scope.toggleCollapse = function(){
				console.log("JHello Wirkd");
				$scope.isCollapsed = !$scope.isCollapsed;
		    }
			//This make the rest call to the server and authenticate the user
			var authenticate = function(credentials, callback) {
				
				$http({
					
					url: 'user',
					method: 'POST',
					data: {
						'username': credentials.username,
						'password': credentials.password
					}
				}).then(function(response, headers){
					
					
					// If the login procedure return success, store the jwt token from response
					store.set('jwt',response.data);
				    $rootScope.authenticated = true;
					callback && callback($rootScope.authenticated);
					
				}, function(error){
					
					$rootScope.authenticated = false;
					callback && callback(false);
				})
			}
			
			// Handles the login click on the page
			$scope.login = function() {
				
				// Call the autheticate method to make post request to server with given credentials
				authenticate($scope.credentials, function(authenticated) {
					
					
					if (authenticated) {
						
						$location.path("/goToHome");						// Redirect to main landing page
						$scope.errorMessage = "";							// Make the errorMessage field on login page to blank
						$scope.error = false;								// Set error flag to false
						$rootScope.authenticated = true;					// Set the root scope variable as authenticated to be used  through out the application
						
					} else {
						
						$location.path("/login");							// Throw user back to login page
						$scope.errorMessage = "Invalid User Credentails";	// Set the message in the login form
						$scope.error = true;								// Set the error flag to true
						$rootScope.authenticated = false;					// Sett the root scope variable as not authenticated 
					}
				})
			};
			
			// Handles logout click on the page
			$scope.logout = function() {
				
				if($scope.authenticated){
					
					$http.post('logoutUrl', {}).success(function() {
						
						$rootScope.authenticated = false;
						store.set('jwt', '');
						$location.path("/");
						
					}).error(function(data) {
						
						$location.path("/");
						store.set('jwt', '');
						$rootScope.authenticated = false;
					});
				} else {
					
					$location.path("/login");
				}
			};
			
		})
	.controller('landing', function($http, $location, $rootScope) {
			
		$http({
			url: '/api/goToHome/',
			method: 'GET'
		}).then(function(response){
			$rootScope.authenticated = true;
			$location.path("/goToHome");
			
		},function(error){
			$rootScope.authenticated = false;
			$location.path("/home");
		})
			
		})
	.controller('main', function($scope, $rootScope, $http, store, $location) {
			
		$http({
			url: '/api/main/',
			method: 'GET'
		}).then(function(response){
			
			$rootScope.authenticated = true;
			console.log(response.data);
			$rootScope.SessionId=response.data.id;
			$scope.greeting = response.data;
			$scope.jwt = store.get('jwt');
			
		},function(error){
			
			$location.path("/");
		})
	})
	.controller('signup', function($scope, $http, store, jwtHelper) {
			
			$scope.signUpDetails = {}
			
			$scope.signUp = function(){
				
				$http({
					url: 'newUserRegistration',
					method: 'POST',
					data:  $scope.signUpDetails
				}).then(function(response){
					
					console.log(response);
					$scope.error = true;
					$scope.errorMessage = "Success";
					
				},function(error){
					
					
				})
			}
			
		})