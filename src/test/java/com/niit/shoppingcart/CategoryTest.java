package com.niit.shoppingcart;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.model.Category;
import com.niit.shoppingcart.model.Product;

public class CategoryTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingcart");
		context.refresh();
		CategoryDAO categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
		Category category = (Category) context.getBean("category");
		
		// Create Operation
		category.setId("CG01");
		category.setName("CGName01");
		category.setDescription("CGDesc01");
	    categoryDAO.saveOrUpdate(category);
		
	    // categoryDAO.delete("CG01"); --> Delete Operation
		
	    //Retrieve Operation
	    
	   System.out.println(categoryDAO.get("CG01").getProducts());
	    if(categoryDAO.get("CG01")== null)
		{
			System.out.println("Category does not exist");
		}
		else
		{
			System.out.println("Category exists..");
			System.out.println();
		} 
	
	
	List<Category>  list =    categoryDAO.list();
	
	for(Category c : list) {
		System.out.println(c.getId() + ":" + c.getName() + ":" + c.getDescription() );
		Set<Product> products  = c.getProducts();
		for(Product p : products) {
			System.out.println("Product :" + p.getId() + ":" + p.getName()+ ":" + p.getDescription());
		}
		
		
		
	}
	
	}
}
