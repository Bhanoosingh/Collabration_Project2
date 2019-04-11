package com.backend_project;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class newblogtest {

	private static AnnotationConfigApplicationContext context;
	
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
