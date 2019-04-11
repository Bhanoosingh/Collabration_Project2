package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Message;

@Repository("chatDao")
@Transactional
public class ChatDaoImpl implements ChatDao{

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean saveMessage(Message msg) {
		try {
			sessionFactory.getCurrentSession().save(msg);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessage() {
		try {
			return sessionFactory.getCurrentSession().createQuery("from Message").list();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
