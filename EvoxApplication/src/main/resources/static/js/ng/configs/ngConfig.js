EvoxApp

		.config(

		function($routeProvider, $httpProvider, $stateProvider) {

			// Setup the route providers
			$routeProvider.when('/', {
				templateUrl : 'home.html',
				controller : 'home'
			}).when('/login', {
				templateUrl : 'login.html',
				controller : 'login'
			}).when('/main', {
				templateUrl : 'main.html',
				controller : 'main'
			}).when('/signUp', {
				templateUrl : 'signup.html',
				controller : 'signup'
			}).when('/goToHome', {
				templateUrl : 'landing.html',
				controller : 'landing'
			}).when('/admin', {
				templateUrl : 'admin.html',
				controller : 'admin'
			}).otherwise('/');

			$stateProvider.state('home', {
				url : '/',
				controller : 'home',
				templateUrl : 'home.html',
				data : {
					requiresLogin : true
				}
			});

			$stateProvider.state('login', {
				url : '/login',
				controller : 'login',
				templateUrl : 'login.html'
			});

			$stateProvider.state('signup', {
				url : '/signUp',
				controller : 'signup',
				templateUrl : 'signup.html'
			});

			// This is added to let the client know that this is not browser
			// request
			// but treat it as XML request
			$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
			$httpProvider.interceptors.push('authInterceptor');

		});

EvoxApp

		.factory(
					'authInterceptor',
					
						function($rootScope, $q, $window, store, $location) {
						
						 var setMessage = function (response) {
				                //if the response has a text and a type property, it is a message to be shown
				                if (response.data.text && response.data.type) {
				                    message = {
				                        text: response.data.text,
				                        type: response.data.type,
				                        show: true
				                    };
				                }
				            };
				            
							return {
								request : function(config) {
									config.headers = config.headers || {};
									if (store.get('jwt')) {
										config.headers.Authorization = 'Bearer '
												+ store.get('jwt');
									}
									return config;
								},
								response : function(response) {
									if (response.status === 401) {
										// handle the case where the user is not authenticated
										$location.path("/");
									}
									return response || $q.when(response);
								},
				                //this is called after each unsuccessful server request
				                responseError : function (response) {
				                	if (response.status === 401) {
										// handle the case where the user is not authenticated
										$location.path("/");
									}
				                    return $q.reject(response);
				                }
							};
					}
				);
