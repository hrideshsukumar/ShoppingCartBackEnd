package com.niit.shoppingcart;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.model.Product;


public class ProductTest {
	

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingcart");
		context.refresh();
		ProductDAO productDAO = (ProductDAO) context.getBean("productDAO");
		Product product = (Product) context.getBean("product");
//		CategoryDAO categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
//		SupplierDAO supplierDAO = (SupplierDAO) context.getBean("supplierDAO");
//		
		// Create Operation
	    product.setId("PR03");
		product.setName("ASUS");
		product.setDescription("Laptop");
		product.setPrice(50000);
		product.setSupplier_id("SP02");
		product.setCategory_id("CG09");
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
