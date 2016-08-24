package com.niit.shoppingcart;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.model.Cart;

public class CartTest {
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingcart");
		context.refresh();
		CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");
		Cart cart = (Cart) context.getBean("cart");
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		ProductDAO productDAO = (ProductDAO) context.getBean("productDAO");
		
		cart.setProductName("Motorola");
		cart.setQuantity(2);
		cart.setStatus('N');
		cart.setPrice(10000);
		cart.setUser(userDAO.get("sutta"));
		cart.setProduct(productDAO.get("PR0456"));
		cartDAO.saveOrUpdate(cart);
//		List<Cart> list = cartDAO.getTotalAmount("sutta");
        System.out.println("Total : " + cartDAO.getTotalAmount("sutta"));
//		System.out.println(cartDAO.get("C01"));
//		 if(cartDAO.get("C01") == null)
//			{
//				System.out.println("Cart does not exist");
//			}
//			else
//			{
//				System.out.println("Cart exists..");
//				System.out.println();
//			} 
//		
		
		cartDAO.checkOut("sutta");
	}

}
