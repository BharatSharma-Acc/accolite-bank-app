package com.accolite.service;

import com.accolite.model.User;

public interface UserService {
	
	User createUser(String userName);
	User fetchUser(Integer userID);
	
	
}
