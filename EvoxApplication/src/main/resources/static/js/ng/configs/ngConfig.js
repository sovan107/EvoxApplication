EvoxApp

		.config(

		function($routeProvider, $httpProvider, $stateProvider) {

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
				url : '/',
				controller : 'home',
				templateUrl : 'home.html',
				data : {
					requiresLogin : true
				}
			});

			$stateProvider.state('login', {
				url : '/login',
				controller : 'navigation',
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
					
						function($rootScope, $q, $window, store) {
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
									}
									return response || $q.when(response);
								}
							};
					}
				);

// EvoxApp
//	
// .run(
//			
// [
// '$rootScope',
// '$location',
// 'store',
//			 	
// function($rootScope, $location, store) {
//
// // register listener to watch route changes
// $rootScope.$on("$routeChangeStart", function(event, next, current) {
//				
// console.log(next);
// //TODO : Check if the jwt is set or not, if not set, redirect user to login
// page
// if(store.get('jwt') !== ''){
//							  
// $location.path('/main');
// } else {
//							  
// $location.path('/');
// }
// });
// }]);
