EventTrackerApp.controller('loginController', ['$scope', '$location', '$http', '$rootScope',
                                               	function($scope, $location, $http, $rootScope){
    
	    $scope.user = {};
	    $scope.user.email = '';
	    $scope.user.password = '';

	    $scope.loginUser = function(user) {
	    	
	        $scope.resetError();

	        $http.post('login/authenticate', user).success(function(login) {                       
	            if(login.sessionId===null) {
	                $scope.setError(login.status);

	                return;
	            }
	            $scope.user.email = '';
	            $scope.user.password = '';  

	            $rootScope.SessionId=login.sessionId;

	            $location.path("/main");

	        }).error(function() {
	            $scope.setError('Invalid user/password combination');
	        });
	    }

	    $scope.resetError = function() {
	        $scope.error = false;
	        $scope.errorMessage = '';
	    }

	    $scope.setError = function(message) {
	        $scope.error = true;
	        $scope.errorMessage = message;
	        $rootScope.SessionId='';
	    }
}]);

EventTrackerApp.controller('mainController', ['$scope',
                                              	function($scope){
   
   $scope.message = 'Hello World';
}]);