package com.niit.shoppingcart;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.model.Product;
import com.niit.shoppingcart.model.Supplier;

public class SupplierTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingcart");
		context.refresh();
		SupplierDAO supplierDAO = (SupplierDAO) context.getBean("supplierDAO");
		Supplier supplier = (Supplier) context.getBean("supplier");
		
		// Create Operation
	    supplier.setId("SP8194");
		supplier.setName("SPName8194");
		supplier.setAddress("SPAddress8194");
	    supplierDAO.saveOrUpdate(supplier);
		System.out.println("NO of suppliers:"+supplierDAO.list().size());
	   
		// supplierDAO.delete("SP8194"); -->Delete Operation 
		  
	    //Retrieve Operation
	    if(supplierDAO.get("CG01")== null)
		{
			System.out.println("Supplier does not exist");
		}
		else
		{
			System.out.println("Supplier exists..");
			System.out.println();
		} 
	    
	    List<Supplier> list = supplierDAO.list();
	    
	    for(Supplier s : list) {
	    	System.out.println(s.getId() + ":" + s.getName() + ":" + s.getAddress());
	    	Set<Product> products = s.getProducts();
	    	
	    	for(Product p : products) {
	    		System.out.println("Product :" + p.getId() + ":" + p.getName() + ":" + p.getPrice() + ":" + p.getDescription());
	    	}
	    }
	}

}
