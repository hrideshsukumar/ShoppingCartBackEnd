package com.niit.shoppingcart;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.model.User;

public class UserTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingcart");
		context.refresh();
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = (User) context.getBean("user");
		
		// Create Operation
	    user.setId("Sutta");
		user.setName("Hridesh");
		user.setAddress("THRISSUR");
		user.setPassword("adima123");
		user.setMail("hrideshh@yahoo.co.in");
	    user.setMobile("9544151378");
	    userDAO.saveOrUpdate(user);
		System.out.println("NO of users:"+userDAO.list().size());
	   
		// userDAO.delete("4dim4"); -->Delete Operation 
		  
	    //Retrieve Operation
	    if(userDAO.get("Sutta")== null)
		{
			System.out.println("User does not exist");
		}
		else
		{
			System.out.println("User exists..");
			System.out.println();
		} 
	}

}
