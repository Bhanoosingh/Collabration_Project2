/**
 * 
 */
app.controller('BlogpostController',function($scope,$location,BlogService,$rootScope,$sce){
	$scope.blogsApproved;
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
		var objarr=[];

		
		for(r=0;r<response.data.length;r++){
			blog = {id:0,blogTitle:'',blogContent:'',approved:'',postedOn:'',likes:0,postedBy:''}
		for(i=0;i<response.data[r].length;i++){
			if(i==0)
				{
				blog.id=response.data[r][i];
				
				}
			if(i==1)
			{
			blog.blogTitle=response.data[r][i];
			}
			if(i==2)
			{
			blog.blogContent=response.data[r][i];
			}
			if(i==3)
			{
				blog.approved=response.data[r][i];
			
			}
			if(i==4)
			{
			blog.postedOn=response.data[r][i];
			}
			if(i==5)
			{
			blog.likes=response.data[r][i];
			}
			if(i==7)
			{
				blog.postedBy=response.data[r][i];
			}
		}
			objarr[r]=blog;
		}
		
		$scope.blogsApproved=objarr //select * from blogpost where approved=true
		alert('blogsApproved approved size ' + $scope.blogsApproved.length);
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


