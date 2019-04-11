package com.niit.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.ChatDao;
import com.niit.model.BlogPost;
import com.niit.model.ErrorClazz;
import com.niit.model.Message;
import com.niit.model.OutputMessage;
import com.niit.model.User;

@RestController
 public class ChatController
 {
	@Autowired
	ChatDao chatDao;
	
        @MessageMapping("/chat")
        @SendTo("/topic/message")
        public OutputMessage sendMessage(Message message)
        {
        	if(chatDao.saveMessage(message)) {
        		System.out.println("Message Recieved");	
        	}
        	
           
        	return new OutputMessage(message,new Date());
        }
        @RequestMapping("/getmessage")
        public ResponseEntity<?> getMessage(HttpSession session){
        	String email=(String)session.getAttribute("loginId");
			/*if(email==null){
				ErrorClazz error=new ErrorClazz(4,"Unauthrozied access.. Please login");
				return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //2nd callback function
			}*/
			
			//List of blogs waiting for approval - only ADMIN can view - Authorized 
			
			
			List<Message> messageList=chatDao.getMessage();//false[ADMIN] or true
			
			return new ResponseEntity<List<Message>>(messageList,HttpStatus.OK);
		
        	
        }
}