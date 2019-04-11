package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Friend;
import com.niit.model.User;
@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {
	@Autowired
	private SessionFactory sessionFactory;
	public List<User> listOfSuggestedUsers(String email) {
		System.out.println("DAO"+email);
		Session session=sessionFactory.getCurrentSession();
		List<User> suggestedUsers=session.createSQLQuery("select * from user_table where email in "
				+ "(select email from user_table where email!=?1 minus "
				+ "(select fromId from friend_table where toId=?2 union "
				+ "select toId from friend_table where fromId=?3))").addEntity(User.class)
				.setString(1, email)
				.setString(2, email)
				.setString(3, email)
				.list();
		
		return suggestedUsers;
		}

	public void addFriendRequest(Friend friend) {
		try {
		Session session=sessionFactory.getCurrentSession();
		session.save(friend);}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	public List<Friend> getAllPendingRequests(String email) {

		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Friend where toId=?1 and status=?2");
		query.setString(1, email);
		query.setCharacter(2, 'P');
		List<Friend> friendRequests=query.list();
		return friendRequests;
	}

	public void updateFriendRequest(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		if(friend.getStatus()=='A')
			session.update(friend);//update friend set status='A' where id=?
		else
			session.delete(friend);//delete from friend where id=?
	}

	public List<User> listOfFriends(String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=
		session.createSQLQuery("select * from user_table where email in"
				+ "(select fromId from friend_table where toId=?1 and status='A' "
				+ " union "
				+ "select toId from friend_table where fromId=?2 and status='A')").addEntity(User.class);
		query.setString(1, email);
		query.setString(2, email);
		List<User> friends=query.list();
		return friends;
	}

}
