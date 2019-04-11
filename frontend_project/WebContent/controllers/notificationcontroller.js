/**
 * 
 */
app.controller('NotificationController',function($scope,$location,NotificationService,$rootScope,$routeParams){
	var id=$routeParams.id;
	//select * from notification where email=? and viewed=0
	function getAllNotification(){
		NotificationService.getAllNotification().then(
				function(response){
				     $rootScope.notifications=response.data
				     $rootScope.notificationsCount=$rootScope.notifications.length
				},
				function(response){
					$rootScope.error=response.data
					if(response.status==401)
						$location.path('/login')
				})
				
	}
	if(id!=undefined){
	NotificationService.getNotification(id).then(function(response){
		$scope.notification=response.data
	},function(response){
		$rootScope.error=response.data
		if(response.status==401)
			$location.path('/login')
	})
	
	NotificationService.updateNotification(id).then(function(response){
           getAllNotification()//select * from notification where email=? and viewed=0		
	},function(response){
		$rootScope.error=response.data
		if(response.status==401)
			$location.path('/login')
	})
	}
	getAllNotification()
})





