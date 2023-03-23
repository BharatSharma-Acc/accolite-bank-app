package com.accolite.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.miniDB.MyDB;
import com.accolite.model.User;

/*
 * This class contains all business logic related with User object
 */
@Service
public class UserServiceImpl implements UserService {
	
	private MyDB myDB;
	
	@Autowired
    public void setMyDB(MyDB myDB) {
        this.myDB = myDB;
    }

	@Override
	public User createUser(String userName) {
		User user = new User(userName);
		myDB.insertUser(user.getUserId(), user);
		
		return user;
	}
	
	@Override
	public User fetchUser(Integer userID) {
		User user = myDB.selectUser(userID);
		
		return user;
	}

}
