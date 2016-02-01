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
        <script src="resources/js/lib/angular/angular.min.js"></script>
        <script src="resources/js/lib/angular/angular-route.min.js"></script>
        <script src="resources/js/eventtracker.js"></script>
        <script src="resources/js/routes.js"></script>
        <script src="resources/js/controllers.js"></script>
        
        
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
            <script src="resources/js/html5shiv.js"></script>
            <script src="resources/js/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <a class="navbar-brand" href="index.jsp">Event Tracker</a>
        </nav>
     	 <div ng-view></div>
    </body>
</html>