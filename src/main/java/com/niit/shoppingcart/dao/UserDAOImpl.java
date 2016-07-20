package com.niit.shoppingcart.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	@Transactional
	public User get(String id) {
		String hql = "from User where id=" + "'" + id + "'";
		@SuppressWarnings("unchecked")
		Query<User> query = sessionFactory.getCurrentSession().createQuery(hql);

		List<User> listUser = query.getResultList();
		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		return null;

	}

	@Transactional
	public void saveOrUpdate(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);

	}

	@Transactional
	public void delete(String id) {
		User UserToDelete = new User();
		UserToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(UserToDelete);

	}

	@Transactional
	public List<User> list() {

		@SuppressWarnings({ "unchecked", "deprecation" })
		List<User> listUser = (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listUser;
	}

}
