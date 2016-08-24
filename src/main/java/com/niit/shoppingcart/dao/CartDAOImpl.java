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
	@Transactional
	public Cart get(int id) {
		String hql = "from Cart where id = '" + id + "' and status = 'N'";
		Query<Cart> query = sessionFactory.getCurrentSession().createQuery(hql);
		
		
		List<Cart> list = query.list();
		
		if(list!=null && !list.isEmpty()) {
			
			return list.get(0);
		}
		return null;
	}

	@Transactional
	public void saveOrUpdate(Cart cart) {
		sessionFactory.getCurrentSession().saveOrUpdate(cart);
		
	}

	@Transactional
	public String delete(int id) {
		Cart cartToDelete = new Cart();
		cartToDelete.setId(id);
		
			try {
				sessionFactory.getCurrentSession().delete(cartToDelete);
			} catch (HibernateException e) {
				
				e.printStackTrace();
				return e.getMessage();
			}
		
		return null;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional
	public long getTotalAmount(String id) {
		String hql = "select sum(price) from Cart where user_ID = '" + id + "' and status = 'N'";
		
		Query<Cart> query = sessionFactory.getCurrentSession().createQuery(hql);
         
          List list = query.list();
          long total = (long) list.get(0);
          if(list!=null && !list.isEmpty()) {
		return total;
          }
          return 0;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional
	public Cart getByUserId(String uID) {
		String hql = "from Cart where user_id = '" + uID + "' and status = 'N'";
		Query<Cart> query = sessionFactory.getCurrentSession().createQuery(hql);
		
		
		List<Cart> list = query.list();
		
		if(list!=null && !list.isEmpty()) {
			
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@Transactional
	public List<Cart> userCartList(String uID) {
		String hql = "from Cart where user_id = '" + uID + "' and status = 'N'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<Cart> list = query.list();
		
		return list;
		
      
	}

	@Transactional
	public void checkOut(String uID) {
		String hql = "update Cart set status = 'Y' where user_id ='" + uID + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

}
