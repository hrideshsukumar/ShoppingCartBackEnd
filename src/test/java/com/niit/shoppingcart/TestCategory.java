package com.niit.shoppingcart;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.model.Category;

public class TestCategory {

	@Autowired
	static Category category;

	@Autowired
	static CategoryDAO categoryDAO;

	
	static AnnotationConfigApplicationContext context;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		category=(Category) context.getBean("category");
		categoryDAO=(CategoryDAO) context.getBean("categoryDAO");
		
	}

	@Test
	public void categoryNameTest() {
		category=categoryDAO.get("CG01");
		String name = category.getName();
		assertEquals("Category Name Test", "CGName01", name);
		
	}
}
