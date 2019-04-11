/**
 * usercontroller
 */
app.controller('UserController',function($scope,userService,$rootScope,$location,$cookieStore,$http){
	$scope.user={"firstname":"","lastname":"","phonenumber":"","password":"","email":"","address":"","role":"","Online":false}
	console.log("in controller");
	// Statement to fetch user details for update [select * from user_table where email=?
	if($rootScope.loggedInUser!=undefined){
	userService.getUserDetails().then(
			function(response){
				alert('success')//only for loggedInUser - only if user is loggedin
				$scope.user=response.data
	},function(response){
		$scope.error=response.data
		$location.path('/login')
	})
	}
	$scope.register=function(){
		userService.register($scope.user).then(
		 function(response){
			alert('Registered successfully.. please login..')
			$location.path('/login')
		 }
		,function(response){
			$scope.error=response.data  //ErrorClazz object
		})
	}
	$scope.login=function(){
		userService.login($scope.user).then(function(response){
			alert('login successfully..')
			$rootScope.loggedInUser=response.data
			console.log(response.data);
			
			$cookieStore.put('loggedInUser',response.data)
			$location.path('/home') //valid credentials
		},function(response){
			$scope.error=response.data //ErrorClazz
			$location.path('/login')
		})
	}
	$scope.update=function(){
		userService.update($scope.user).then(function(response){
			alert("updated the details successfully...")
			$rootScope.loggedInUser=response.data//updated user object
			$cookieStore.put('loggedInUser',response.data)
			$location.path('/home')
		},function(response){
			console.log(response.status)
			if(response.status==401)
				$location.path('/login')
		})
	}
})