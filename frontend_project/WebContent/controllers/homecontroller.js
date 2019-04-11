/**
 * 
 */
app.controller('HomeController',function($scope,$rootScope,$location,NotificationService){
NotificationService.getAllNotification().then (
	function(response){
		$rootScope.notification=response.data
		$rootScope.notificationCount=$rootScope.notification.length
	},
	function(response){
		$rootScope.error=response.data
		if(response.status==401)
			$location.path('/login')
	})
})
