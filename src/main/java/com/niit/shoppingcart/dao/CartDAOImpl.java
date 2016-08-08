package com.niit.shoppingcart.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.model.Cart;

@Repository("cartDAO")
public class CartDAOImpl implements CartDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	Cart cart;
	
	public CartDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Cart> list() {
		
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<Cart> list = sessionFactory.getCurrentSession().createCriteria(Cart.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		return list;
	}


	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public Cart get(String id) {
		String hql = "from Cart where userID = '" + id + "' and status = 'N'";
		Query<Cart> query = sessionFactory.getCurrentSession().createQuery(hql);
		
		
		List<Cart> list = query.list();
		
		if(list!=null && !list.isEmpty()) {
			
			return list.get(0);
		}
		return null;
	}

	@Override
	public void saveOrUpdate(Cart cart) {
		sessionFactory.getCurrentSession().saveOrUpdate(cart);
		
	}

	@Override
	public String delete(String id) {
		cart.setUserID(id);
		
			try {
				sessionFactory.getCurrentSession().delete(cart);
			} catch (HibernateException e) {
				
				e.printStackTrace();
				return e.getMessage();
			}
		
		return null;
	}

	@SuppressWarnings({ "deprecation", "unchecked", "unused" })
	@Override
	public int getTotalAmount(String id) {
		String hql = "select sum(price) from Cart where userID = '" + id + "'";
		
		Query<Cart> query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return 0;
	}

}
