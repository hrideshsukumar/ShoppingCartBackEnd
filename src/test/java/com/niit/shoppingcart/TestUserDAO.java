package com.niit.shoppingcart;

import static org.junit.Assert.*;

import org.junit.AfterClass;
//import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.model.User;

public class TestUserDAO {

	// The required beans
	// User and UserDAO from the context

	@Autowired
	static UserDAO userDAO;
	@Autowired
	static User user;
	static AnnotationConfigApplicationContext context;
	// @Before will execute always before executing testcases
	// @BeforeClass will execute only once(can only be used for static methods)

	@BeforeClass
	public static void init() {
		System.out.println("Check Init");
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
		user = (User) context.getBean("user");

	}
	@AfterClass
	public static void closeResource() {
		context.close();
		userDAO = null;
		user = null;
	}

	// select count(*) from user
	@Test
	public void userTestCase() {

		int size = userDAO.list().size();
		assertEquals("User list test case", 2, size);

	}

	// select name from user where id = 'sutta'
	@Test
	public void userNameTestCase() {
		user = userDAO.get("Sutta");
		String name = user.getName();
		assertEquals("Name test case", "Hridesh Sukumar N H", name);

	}

}
