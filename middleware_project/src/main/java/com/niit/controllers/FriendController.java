package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.FriendDao;
import com.niit.model.ErrorClazz;
import com.niit.model.Friend;
import com.niit.model.User;

@Controller
public class FriendController {

	@Autowired
private FriendDao friendDao;
@RequestMapping(value="/suggestedusers",method=RequestMethod.GET)
public ResponseEntity<?> getSuggestedUsersList(HttpSession session){
	String email=(String)session.getAttribute("loginId");
	System.out.println("Current user:-"+email);
	if(email==null){
		ErrorClazz error=new ErrorClazz(4,"Unauthrozied access.. Please login");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //2nd callback function
	}
	List<User> suggestedUsers=friendDao.listOfSuggestedUsers(email);
	System.out.println("List"+suggestedUsers);
	return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
}
@RequestMapping(value="/addfriend",method=RequestMethod.POST)
public ResponseEntity<?> addFriendRequest(@RequestBody User toUser,HttpSession session){
	String email=(String)session.getAttribute("loginId");
	System.out.println("login id------------------"+email);
	if(email==null){
		ErrorClazz error=new ErrorClazz(4,"Unauthrozied access.. Please login");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //2nd callback function
	}
	//fromId -> email
	//toId -> toUser.getEmail()
	Friend friend=new Friend();
	friend.setFromId(email); //john.s@xyz.com
	friend.setToId(toUser.getEmail()); //james.s@xyz.com
	friend.setStatus('P');//Pending
	friendDao.addFriendRequest(friend);
	return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	
	
	
}
@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
 public ResponseEntity<?> pendingRequests(HttpSession session){
	 String email=(String)session.getAttribute("loginId");
		if(email==null){
			ErrorClazz error=new ErrorClazz(4,"Unauthrozied access.. Please login");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //2nd callback function
		}
		List<Friend> friendRequests=friendDao.getAllPendingRequests(email);
		return new ResponseEntity<List<Friend>>(friendRequests,HttpStatus.OK);
 }
@RequestMapping(value="/updatependingrequest",method=RequestMethod.PUT)
public ResponseEntity<?> updatePendingRequest(@RequestBody Friend friend,HttpSession session){
	String email=(String)session.getAttribute("loginId");
	if(email==null){
		ErrorClazz error=new ErrorClazz(4,"Unauthrozied access.. Please login");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //2nd callback function
	}
	friendDao.updateFriendRequest(friend);//friend status is updated to 'A' or 'D'
	return new ResponseEntity<Friend>(friend,HttpStatus.OK);
}
@RequestMapping(value="/friends",method=RequestMethod.GET)
public ResponseEntity<?> listOfFriends(HttpSession session){
	String email=(String)session.getAttribute("loginId");
	if(email==null){
		ErrorClazz error=new ErrorClazz(4,"Unauthrozied access.. Please login");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //2nd callback function
	}
	List<User> friends=friendDao.listOfFriends(email);
	return new ResponseEntity<List<User>>(friends,HttpStatus.OK);
}
}



