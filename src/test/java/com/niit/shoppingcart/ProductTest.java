package com.niit.shoppingcart;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.model.Product;

public class ProductTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingcart");
		context.refresh();
		ProductDAO productDAO = (ProductDAO) context.getBean("productDAO");
		Product product = (Product) context.getBean("product");
		
		// Create Operation
	    product.setId("PR02");
		product.setName("ASUS");
		product.setDescription("Laptop");
		product.setPrice(50000);
	    productDAO.saveOrUpdate(product);
		System.out.println("NO of products:"+productDAO.list().size());
	   
		// productDAO.delete("PR02"); -->Delete Operation 
		  
	    //Retrieve Operation
	    if(productDAO.get("PR01")== null)
		{
			System.out.println("Product does not exist");
		}
		else
		{
			System.out.println("Product exists..");
			System.out.println();
		} 
	}

}
