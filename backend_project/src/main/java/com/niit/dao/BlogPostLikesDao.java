package com.niit.dao;

import com.niit.model.BlogPost;
import com.niit.model.BlogPostLikes;

public interface BlogPostLikesDao {
	BlogPostLikes hasUserLikedPost(int postId,String email);
	BlogPost updateLikes(int postId,String email);
}
