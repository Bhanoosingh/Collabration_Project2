/**
 * 
 */
app.controller('BlogController',function($scope,$location,BlogService,$rootScope,$sce){

	$scope.addBlog=function(blog){
		BlogService.addBlog(blog).then(function(response){
			alert('Blog is added successfully and it is waiting for approval')
			$location.path('/home')
		},function(response){
			$rootScope.error=response.data//ErrorClazz
			if(response.status==401)//Not Authenticated
				$location.path('/login')					
		})
	}
	//List of blogs waiting for approval
	console.log($rootScope.loggedInUser.role);
	if($rootScope.loggedInUser.role=='ADMIN'){
	BlogService.getBlogsWaitingForApproval().then(
			function(response){
				$scope.blogsWaitingForApproval=response.data //select * from blogpost where approved=false
				alert('blogs waiting for approval size ' + $scope.blogsWaitingForApproval.length)
			},
			function(response){
				$rootScope.error=response.data
				if(response.status==401)
					$location.path('/login')
			})
	}
	//List of blogs approved
	BlogService.getApprovedBlogs().then(function(response){
		$scope.blogsApproved=response.data //select * from blogpost where approved=true
		alert('blogs approved size ' + $scope.blogsApproved.length)
	},function(response){
		$rootScope.error=response.data
		if(response.status==401)
			$location.path('/login')
	})
	
	$scope.addBold=function(blog){
		if(blog.blogContent==undefined)
			blog.blogContent=""
			blog.blogContent=blog.blogContent + "<b></b>"
	}
	
	$scope.addParagraph=function(blog){
		if(blog.blogContent==undefined)
			blog.blogContent=""
		blog.blogContent=blog.blogContent + "<p></p>"
	}
	$scope.addBreak=function(blog){
		if(blog.blogContent==undefined)
			blog.blogContent=""
		blog.blogContent=blog.blogContent + "<br>"
	}
	$scope.addHeading=function(blog){
		if(blog.blogContent==undefined)
			blog.blogContent=""
		blog.blogContent=blog.blogContent + $scope.heading
	}
	
})

