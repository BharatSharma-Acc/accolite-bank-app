package com.accolite.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.accolite.miniDB.MyDB;
import com.accolite.model.Account;
import com.accolite.model.User;
import com.accolite.service.AccountServiceImpl;

// This class is used to generate Two default users with Id's : 2000 and 2001 
// on application Load Time
@Component
public class BankBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	MyDB myDB;
	

	private void createData(){
		
		//First User Creation 
		User user1 = new User();
		user1.setUserName("Tester1");
		user1.setUserId(2000);
		myDB.insertUser(2000, user1);
		
		//Second User Creation
		User user2 = new User();
		user2.setUserName("Tester2");
		user2.setUserId(2001);
		myDB.insertUser(2001, user2);
		
		
	}
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		createData();
	}

}
