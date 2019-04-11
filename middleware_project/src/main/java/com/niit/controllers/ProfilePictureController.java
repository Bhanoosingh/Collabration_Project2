package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.ProfilePictureDao;
import com.niit.model.ErrorClazz;
import com.niit.model.ProfilePicture;

@Controller
public class ProfilePictureController {
	@Autowired
	private ProfilePictureDao profilePictureDao;
		@RequestMapping(value="/uploadprofilepic",method=RequestMethod.POST)
		//directly from html file.
		public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession session){
			String email=(String)session.getAttribute("loginId");
			if(email==null){
				ErrorClazz error=new ErrorClazz(4,"Unauthrozied access.. Please login");
				return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //2nd callback function
			}
			ProfilePicture profilePicture=new ProfilePicture();
			profilePicture.setEmail(email);
			profilePicture.setImage(image.getBytes());
			profilePictureDao.upload(profilePicture);//insert or update 
			return new ResponseEntity<ProfilePicture>(profilePicture,HttpStatus.OK);
		}
		//<img src="http://localhost:..../middleware/getimage/nameoftheuser" alt="image not found">
		//NO HTTPSTATUS CODE
		//ONLY BYTE[]
		@RequestMapping(value="/getimage/{email:.+}",method=RequestMethod.GET)
		public @ResponseBody byte[] getImage(@PathVariable String email,HttpSession session){
			String auth=(String)session.getAttribute("loginId");
			if(auth==null){
				return null;
			}
			System.out.println(email);
			ProfilePicture profilePicture=profilePictureDao.getProfilePic(email);
			
			if(profilePicture==null)
				return null;
			System.out.println("Image is "  + profilePicture.getImage() + " " + email);
			return profilePicture.getImage();
		}
		}


