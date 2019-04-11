package com.niit.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.JobDao;
import com.niit.dao.UserDao;
import com.niit.model.ErrorClazz;
import com.niit.model.Job;
import com.niit.model.User;

@RestController
public class JobController {
	@Autowired
	private JobDao jobDao;
		@Autowired
	private UserDao userDao;
		@RequestMapping(value="/addjob",method=RequestMethod.POST)
		public ResponseEntity<?> addJob(@RequestBody Job job,HttpSession session){
		String email=(String)session.getAttribute("loginId");
			if(email==null){//not logged in [Authenticated]
				ErrorClazz error=new ErrorClazz(4,"Unauthrozied access.. Please login");
				return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //login page
			}
			User user=userDao.getUser(email);
			if(!user.getRole().equals("ADMIN")){//logged in but role is not Admin
				ErrorClazz error=new ErrorClazz(5,"Access Denied");
				return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //login page
			}
			try{
				job.setPostedOn(new Date());
			jobDao.addJob(job);
			return new ResponseEntity<Job>(job,HttpStatus.OK);
			}catch(Exception e){
				ErrorClazz error=new ErrorClazz(6,"Unable to insert job details..some fields are empty " );
				return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
	    @RequestMapping(value="/getalljobs",method=RequestMethod.GET)	
		public ResponseEntity<?> getAllJobs(HttpSession session){
	    	String email=(String)session.getAttribute("loginId");
			/*if(email==null){//not logged in [Authenticated]
				ErrorClazz error=new ErrorClazz(4,"Unauthrozied access.. Please login");
				return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //login page
			}
			*/List<Job> jobs=jobDao.getAllJobs();
			return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);//success
		}
	    @RequestMapping(value="/getjob/{id}",method=RequestMethod.GET)
	    public ResponseEntity<?> getJob(@PathVariable int id, HttpSession session){
	    	String email=(String)session.getAttribute("loginId");
			if(email==null){//not logged in [Authenticated]
				ErrorClazz error=new ErrorClazz(4,"Unauthrozied access.. Please login");
				return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //login page
			}
			Job job=jobDao.getJob(id);
			return new ResponseEntity<Job>(job,HttpStatus.OK);
	    }
	}


