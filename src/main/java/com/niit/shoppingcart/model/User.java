package com.niit.shoppingcart.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class User implements Serializable {
	@Id
	private String id;
	
	private String name;
	
	private String address;
	
	private String password;


	private String mail;
	
	
	private String mobile;
	// update user set admin=1 where name = 'admin'
	@Column(name = "admin" , columnDefinition= "tinyinit default 0")
	private byte admin;
    
	@OneToMany(mappedBy = "user" , fetch = FetchType.EAGER)
    private Set<Cart> cart	;

	public byte getAdmin() {
		return admin;
	}
	
	public Set<Cart> getCart() {
		return cart;
	}

	public void setCart(Set<Cart> cart) {
		this.cart = cart;
	}

	

	public void setAdmin(byte admin) {
		this.admin = admin;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
