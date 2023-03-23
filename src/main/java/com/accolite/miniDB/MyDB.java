package com.accolite.miniDB;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

import com.accolite.model.Account;
import com.accolite.model.User;

/*
 * This class is used as InMemory DB
 *  We use two static Maps : UserMap and AccountMap to store User and Account related data
 *  USerMap will be as : UserID as Key, User Object as Value where User Object will contains list of accounts associated with that user
 *  AccountMap will be as : AccountID as key, Account object as Value where Account object contains user ID associated with account. 
 *  UserMap and AccountMap will be created when MyDB class is loaded
 *  This class contains basic methods to add, remove, fetch data from UserMap and AccountMap
 *  This class will be replaced by Actual DB  
 */
@Configuration
public class MyDB {
	
	// Declaring the static map
    private static Map<Integer, User> userMap;
    private static Map<Integer, Account> accountMap;
    
  
    // Instantiating the static map
    static
    {
    	userMap = new HashMap<Integer, User>();
    	accountMap = new HashMap<Integer, Account>();
    }
    
    public void insertAccount(Integer accountId, Account account){
    	accountMap.put(accountId, account);
    }
    
    public void insertUser(Integer userID, User user){
    	userMap.put(userID, user);
    }

    public User selectUser(Integer userID){
    	return userMap.get(userID);
    }
    
    public User updateUser(User user){
    	return userMap.put(user.getUserId(), user);
    }
    
    public Account updateAccount(Account account){
    	return accountMap.put(account.getAccountId(), account);
    }
    
    public Account selectAccount(Integer accountId){
    	return accountMap.get(accountId);
    }
    
    public Account deleteAccount(Integer accountId){
    	return accountMap.remove(accountId);
    }
    
    
    
    
}
