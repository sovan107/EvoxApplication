<!DOCTYPE html>
<html ng-app="EventTrackerApp">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="This application is event tracking application.">
        <title>Event Tracker</title>
        <link href='http://fonts.googleapis.com/css?family=Lato:300,400' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Abel' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/eventtracker.css">
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
            <script src="resources/js/html5shiv.js"></script>
            <script src="resources/js/respond.min.js"></script>
        <![endif]-->
        <style type="text/css">
			[ng\:cloak], [ng-cloak], .ng-cloak {
				display: none !important;
			}
		</style>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <a class="navbar-brand" href="index.jsp">Event Tracker</a>
        </nav>
     	 <body ng-cloak class="ng-cloak">
	<div ng-controller="navigation" class="container tpad">
		<ul class="nav nav-pills" role="tablist">
			<li ng-class="{active:tab('home')}"><a href="#/">home</a></li>
			<li><a href="#/login">login</a></li>
			<li ng-show="authenticated"><a href="" ng-click="logout()">logout</a></li>
		</ul>
	</div>
	<div ng-view class="container tpad"></div>
		<script src="https://code.angularjs.org/1.4.2/angular.js" type="text/javascript"></script>
		<script src="https://code.angularjs.org/1.4.2/angular-route.js"></script>
        <script src="resources/js/eventtracker.js"></script>
        <script src="resources/js/routes.js"></script>
        <script src="resources/js/controllers.js"></script>
</body>
</html>