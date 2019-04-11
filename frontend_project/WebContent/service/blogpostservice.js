/**
 * 
 */
app.factory('BlogService',function($http){
	var blog={}
	var BASE_URL="http://localhost:8081/middleware_project"
	
	blog.addBlog=function(blog){
		return $http.post(BASE_URL + "/addblogpost",blog)
	}
	
	blog.getBlogsWaitingForApproval=function(){
		return $http.get(BASE_URL + "/getblogs/"+false)
	}
	
	blog.getApprovedBlogs=function(){
		return $http.get(BASE_URL + "/getblogs/"+true)
	}
    blog.getBlog=function(id){
    	return $http.get(BASE_URL + "/getblog/"+id)
    }
    
    blog.hasUserLikedPost=function(id){
    	return $http.get(BASE_URL + "/haspostliked/"+id);//response.data -> BlogPostLikes [1 / null]
    }
    //when glyphicon-thumps-up is clicked
    blog.updateLikes=function(id){
    	return $http.put(BASE_URL + "/updatelikes/"+id);//response.data -> BlogPost object with updated likes
    	// http://localhost:9090/co..../updatelikes/735
    }
	return blog;
})