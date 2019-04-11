package com.niit.dao;

import com.niit.model.ProfilePicture;

public interface ProfilePictureDao {
	void upload(ProfilePicture profilePicture);
	ProfilePicture getProfilePic(String email);
	}

