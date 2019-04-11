
var app=angular.module("app",['ngRoute','ngCookies'])
app.config(function($routeProvider){
	$routeProvider
	.when('/register',{
		templateUrl:'views/registrationform.html',
		controllers:'usercontroller'
	})
	.when('/login',{
		templateUrl:'views/login.html',
		controllers:'usercontroller'
	})
	.when('/editprofile',{
		templateUrl:'views/updateform.html',
		controllers:'usercontroller'
	})
	.when('/home',{
		templateUrl:'views/home.html'
	})
	.when('/addjob',{  //V to C
		templateUrl:'views/jobform.html',//ng-model
		controllers:'Jobcontroller' //$scope.job
	})
	.when('/getalljobs',{//C to V
		templateUrl:'views/joblist.html',  //ng-repeat
		controllers:'JobController'// $scope.jobs
	})
	.when('/getjob/:id',{// C to V
		templateUrl:'views/jobdetail.html',
		controllers:'JobController'//$scope.job=response.data
	})
	.when('/addblog',{
		templateUrl:'views/blogform.html',
		controllers:'blogcontroller'
	})
	.when('/getblogs',{
		templateUrl:'views/bloglist.html',
		controllers:'blogcontroller'
	})
	.when('/getblog/:id',{
		templateUrl:'views/blogpostdetail.html',
		controller:'BlogPostDetailController'
	})
	.when('/chat',{
		templateUrl:'views/chat.html',
		controller:'ChatCtrl'
	})
	
	.when('/suggestedusers',{
		templateUrl:'views/suggestedusers.html',
		controller:'FriendCtrl'
	})
	.when('/pendingrequests',{
		templateUrl:'views/pendingrequests.html',
		controller:'FriendCtrl'
	})
	.when('/getnotification/:id',{
		templateUrl:'views/NotificationDetails.html',
		controller:'NotificationController'
	})
	
	.when('/profilepic',{
		templateUrl:'views/uploadprofilepic.html'
	})
	
	.when('/friends',{
		templateUrl:'views/friendslist.html',
		controller:'FriendCtrl'
	})


	.when('/getblog/:id',{
		templateUrl:'views/blogpostdetail.html',
		controllers:'blogpostdetailcontroller'
	})
	.otherwise({
		templateUrl:'views/login.html',
		controllers:'usercontroller'
	})
})
app.run(function($rootScope,$cookieStore,userService,$location){
	if($rootScope.loggedInUser==undefined)
		$rootScope.loggedInUser=$cookieStore.get('loggedInUser')
		
		$rootScope.logout=function(){
		userService.logout().then(function(response){
			    $rootScope.successMessage="LoggedOut Successfully.."
			    delete $rootScope.loggedInUser
			    $cookieStore.remove("loggedInUser")
				$location.path('/login')
		},function(response){
			$rootScope.errorMessage="Please login.."
				$location.path('/login')
		})
	}
	
})
