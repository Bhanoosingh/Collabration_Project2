package com.backend_project;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogPostDao;
import com.niit.model.BlogPost;

public class Blog {

	static BlogPost blogpost;
	static BlogPostDao blogpostDao;
	@SuppressWarnings("resource")
	@BeforeClass
	public static void init() {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		blogpost =new BlogPost();
		blogpostDao = (BlogPostDao) context.getBean("blogpostDao");
	} 
	
@Ignore
	@Test
	public void testAddBlogPost() {
		blogpost.setBlogTitle("Hibernate");
		
		blogpost.setBlogContent("Hibernate Content");
		
		
		blogpost.setLikes(4);
		blogpostDao.addBlogPost(blogpost);
		
		System.out.println("<-----------Successfully added into blog-------->");
	}

	@Test
	public void testGetBlogs() {
		assertNotNull("no value is returned", blogpostDao.getBlogs(false));
		System.out.println("--------------------------------------------Approved blog size"+blogpostDao.getBlogs(false).size());
	}
/*@Ignore
	@Test
	public void testGetBlogById() {
		fail("Not yet implemented");
	}
*/
}
