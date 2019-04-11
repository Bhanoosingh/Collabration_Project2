/**
 * 
 */
app.factory('FriendService',function($http){
	var friendService={}
	var BASE_URL = "http://localhost:8081/middleware_project"
	friendService.getSuggestedUsers=function(){
		return $http.get(BASE_URL+ "/suggestedusers")
	}
	
	friendService.addFriend=function(user){//toId in friend table
		return $http.post(BASE_URL + "/addfriend",user)
	}
	friendService.getPendingRequests=function(){
		return $http.get(BASE_URL + "/pendingrequests")
	}
	friendService.updateFriendRequest=function(friendRequest){
		//{'id':754,'fromId':'john.s@xyz.com','toId':'james.s@xyz.com',status:'A'}
		return $http.put(BASE_URL + "/updatependingrequest",friendRequest)
	}
	friendService.listOfFriends=function(){
		return $http.get(BASE_URL + "/friends")
	}
	
	return friendService;
})