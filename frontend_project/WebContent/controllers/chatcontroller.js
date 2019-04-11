
app.controller('ChatCtrl', function($scope,$rootScope,chatService,$http)
{
	$scope.messages;
	$scope.message="";
	$scope.max=250;
	
	
	
	chatService.receive().then(function(message){
		$scope.messages=message.data;
		
		});

	$scope.addMessage=function()
	{
		chatService.send($rootScope.loggedInUser.firstname+":" +$scope.message);
		$scope.message="";
		
		chatService.receive().then(function(message){
			$scope.messages=message.data;
			
			});
	};

	

});