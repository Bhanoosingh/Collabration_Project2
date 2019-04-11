/**
 * 
 */

app.factory('userService',function($http){
	var userService ={}
	var BASE_URL = "http://localhost:8081/middleware_project"
		
		userService.register=function(user){
		return $http.post(BASE_URL +"/register",user);
	}
	userService.login=function(user){
		return $http.post(BASE_URL +"/login",user)
	}
	
	userService.logout=function(user){
		return $http.put(BASE_URL +"/logout")
	}
	
	userService.getUserDetails=function(user){
		return $http.get(BASE_URL +"/getuser")
	}
	
	userService.update=function(user){
		return $http.put(BASE_URL +"/update",user)
	}
	
	return userService;
})
	
