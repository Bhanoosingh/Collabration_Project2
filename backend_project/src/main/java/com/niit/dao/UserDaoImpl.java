package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.User;
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
private SessionFactory sessionFactory;
	
	public void registration(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.save(user);//insert into user values (...)
	}

	public boolean isEmailValid(String email) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class,email);
		if(user==null)//email is unique ,valid
			return true;
		else//email is duplicate,invalid
		return false;
	}
	public User login(User user) {
		Session session=sessionFactory.getCurrentSession();
		user=(User)session.createQuery("from User where email=:email and password=:password").setParameter("email", user.getEmail()).setParameter("password", user.getPassword()).uniqueResult();
		//1 user object or null value
		return user;// 1 user object[valid]  / null value [invalid credentials]
	}

	public void update(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.update(user);//update User set email,password,firstname...lastname,online where email=?
	}

	public User getUser(String email) {
		Session session=sessionFactory.getCurrentSession();
		/*User user=(User)session.get(User.class, email);*/
		User user=(User)session.createQuery("from User where email=:email").setParameter("email", email).uniqueResult();
		return user;
	}
}
