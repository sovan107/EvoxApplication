EvoxApp
	.controller('home', function($scope) {
		
		// Take them to signup page
		$scope.signUp = function() {
			
			$location.path('/signUp');
		};
	});