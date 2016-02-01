// Route
EventTrackerApp.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider){
   
    $routeProvider
        .when('/login', {
            templateUrl:'pages/login/login.html',
            controller: 'loginController'
        })
        .when('/main', {
        
            templateUrl:'pages/main/main.html',
            controller: 'mainController'
        })
        
    $routeProvider.otherwise({redirectTo: '/login'});
    
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}]);

//Login validation. 
//!!!!!! ATTENTION !!!!!!!
//If session id is expired, $rootScope.SessionID should be set to null or empty string!!!
EventTrackerApp.run(['$rootScope', '$location', function($rootScope, $location) {

  // register listener to watch route changes
  $rootScope.$on("$routeChangeStart", function(event, next, current) {

      console.log("Routechanged sessionId=" + $rootScope.SessionId);

      if ($rootScope.SessionId == '' || $rootScope.SessionId == null) {

          // no logged user, we should be going to #login
          if (next.templateUrl == "pages/login/login.html") {
              // already going to #login, no redirect needed
          } else {
              // not going to #login, we should redirect now
              $location.path("/login");
          }
      }
  });
}]);