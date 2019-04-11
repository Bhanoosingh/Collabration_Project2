package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.BlogPost;
import com.niit.model.BlogPostLikes;
import com.niit.model.User;
@Repository
@Transactional
public class BlogPostLikesDaoImpl implements BlogPostLikesDao {
	@Autowired
	private SessionFactory sessionFactory;

	public BlogPostLikes hasUserLikedPost(int postId, String email) {
		Session session=sessionFactory.getCurrentSession();
		//HQL => from BlogPostLikes where blogPost.id=? and user.email=?
		//SQL => select * from Blobpostlikes_table where blogpost_id=? and user_email=?
		//Logged in james.smith@xyz.com
		// Views the blogpost id 100
		//select * from blogpost where id=100
		//select * from blogpostlikes where blogpost_id=100 and user_email='james.smith@xyz.com'
		//blogpostdetails.html -> 1 query blogpostdetail 2nd query - glyphicon color
		Query query=session.createQuery("from BlogPostLikes where blogPost.id=? and user.email=?");
		query.setInteger(0, postId);
		query.setString(1, email);
		BlogPostLikes blogPostLikes=(BlogPostLikes)query.uniqueResult();
		return blogPostLikes;// 1 object / null -> glyphicon color[1 object -blue color  , null - black color]
	}
     // when user clicks the glyphicon in frontend


	public BlogPost updateLikes(int postId, String email) {
		//postId=735
				//email='john.s@xyz.com'
				Session session = sessionFactory.getCurrentSession();
				//check the previous condition - if the post is already liked by the user or not
				//by executing the query
				// select * from blogpostlikes where blogpost_id=735 and user_email='john.s@xyz.com'
				// it will return 1 object. blogPostlikes table there is 1 record for this blopost and user
				BlogPostLikes blogPostLikes=hasUserLikedPost(postId, email);
				//for blogPostlikes [null or 1 object]
				BlogPost blogPost=(BlogPost)session.get(BlogPost.class, postId);
				//user has not yet liked the blog post, now the user has liked it
				if(blogPostLikes==null){//previous condition- currently glyphicon is in black color 
					//likes  -> [insert and increment the likes]
					BlogPostLikes likes=new BlogPostLikes();//id, blogpost_id, user_email
					User user=(User)session.get(User.class, email);
					likes.setBlogPost(blogPost);
					likes.setUser(user);
					session.save(likes);//insert into blogpostlikes 
					blogPost.setLikes(blogPost.getLikes() + 1); //increment the likes
					session.update(blogPost);//update blogpost set likes=likes +1 where id=?
				}
				else{//unlike [previous - liked the blogpost, now user is unliking it]
					session.delete(blogPostLikes);//delete from blogPostLikes where likesid=?
					blogPost.setLikes(blogPost.getLikes() - 1);//decrement the number of likes
					session.update(blogPost);//update blogpost set likes = likes -1 where id=?
				}
				return blogPost;//in frontend we have to display the updated likes
			}

		}


