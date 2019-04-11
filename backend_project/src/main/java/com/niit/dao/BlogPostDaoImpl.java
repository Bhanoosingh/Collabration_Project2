package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.BlogPost;

@Repository("blogpostDao")
@Transactional
public class BlogPostDaoImpl implements BlogPostDao {

	@Autowired
	 SessionFactory sessionFactory;
	public void addBlogPost(BlogPost blogPost) {
		Session session=sessionFactory.getCurrentSession();
		session.save(blogPost);
		
	}

	@SuppressWarnings("unchecked")
	public List<BlogPost> getBlogs(boolean approved) {
		if(approved) {
		
		return sessionFactory.getCurrentSession().createSQLQuery("select * From BLOGPOST_TABLE where approved=:approved").addEntity(BlogPost.class).setParameter("approved",1).list();
		}
		else {
			return sessionFactory.getCurrentSession().createSQLQuery("select * From BLOGPOST_TABLE where approved=:approved").addEntity(BlogPost.class).setParameter("approved",0).list();
		}
		
		//select * from blogpost_table where approved=1
		//or
		//select * from blogpost_table where approved=0
	}

	public BlogPost getBlogById(int id) {
		
		Session session = sessionFactory.openSession();
		//BlogPost blogPost = (BlogPost) session.createSQLQuery("select * from BLOGPOST_TABLE where id=:id").setParameter("id", id);
		
		return (BlogPost) session.get(BlogPost.class, id);
	}

}
