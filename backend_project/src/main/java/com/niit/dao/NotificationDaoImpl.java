package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Notification;
@Repository
@Transactional
public class NotificationDaoImpl implements NotificationDao {

	@Autowired
	private SessionFactory sessionFactory;
		//select * from notification where email=? and viewed=0
	@SuppressWarnings("unchecked")
	public List<Notification> getAllNotification(String email) {
		Session session=sessionFactory.getCurrentSession();
		try {
		return session.createQuery("from Notification where email=:email and viewed=0").setParameter("email", email).list();}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//update notification set viewed=1 where id=?
	public void updateViewedStatus(int notificationId) {
		Session session=sessionFactory.getCurrentSession();
		Notification notification=(Notification)session.get(Notification.class, notificationId);
		notification.setViewed(true);
		session.update(notification);

	}
	//select * from notificaton where id=?
	public Notification getNotification(int id) {
		Session session=sessionFactory.getCurrentSession();
		Notification notification=(Notification)session.get(Notification.class, id);
		return notification;
	}

}
