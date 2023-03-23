package com.accolite.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.miniDB.MyDB;
import com.accolite.model.Account;
import com.accolite.model.Transaction;
import com.accolite.model.User;

/*
 * This class contains all business logic related with Account object
 */
@Service
public class AccountServiceImpl implements AccountService {
	
private MyDB myDB;
	
	@Autowired
    public void setMyDB(MyDB myDB) {
        this.myDB = myDB;
    }

	//Method is used to fetch all accounts associated with User ID from Local DB(In Memory)
	@Override
	public Iterable<Integer> listAllAccounts(Integer userID) {
		User user = myDB.selectUser(userID);
		if(user!=null)
			return user.getAccounts();
		else return null;
	}

	//Method is used to fetch account details using account ID from Local DB(In Memory)
	@Override
	public Account getAccountById(Integer accountId) {
		return myDB.selectAccount(accountId);
	}

	//Method is used to create new Account to be linked with UserID
	//First it will make check whether UserId is valid or not then it will insert new Account entry in Account HashMap.
	//Then it will update new Account's AccountID in User map against Userid. 
	//Basically userid is behaving as foreign key in Account map and List of AccountID will be available in User Map
	@Override
	public Account createAccount(Integer userID) {
		Account account = null;
		User user = myDB.selectUser(userID);
		
		if (user !=null){
			account = new Account(userID);
			
			myDB.insertAccount(account.getAccountId(), account);
			
			List<Integer> accounts = user.getAccounts();
			if(accounts == null)
				accounts = new ArrayList<Integer>();
				
			accounts.add(account.getAccountId());
			user.setAccounts(accounts);
			
			myDB.updateUser(user);
		}
		
		return account;
	}
	
	//Method is used to create new Account to be linked with UserID
	@Override
	public Account deleteAccount(Integer accountId) {
		// TODO Auto-generated method stub
		
		Account account = myDB.deleteAccount(accountId);
		
		if(account !=null){
			Integer userID = account.getUserID();
			User user = myDB.selectUser(userID);
			List<Integer> accList = user.getAccounts();
			accList.remove(account.getAccountId());
			
			user.setAccounts(accList);
			
			myDB.updateUser(user);
		}
		
		return account;
	}
	
	
	/*
	 * This method contains business logic required for Withdrawal or Deposit Transction
	 * Basic business rules covered 1) Withdrawal amount cant be greater than $10000
	 * 						        2) Withdrawal amount should not make current balance below $100
	 *                              3) User cant withdraw more than 90% of current balance in single transaction. 
	 * This method will return Transaction object which contains : Pre , Post balance, account Id, transaction Type, Message 
	 */
	@Override
	public Transaction withOrDepoTrans(Integer accountId, String transType, BigDecimal amount) {
		BigDecimal currBal = new BigDecimal(0);
		Transaction trans = new Transaction();
		
		Account account = myDB.selectAccount(accountId);
		
		trans.setAccountID(accountId);
		trans.setTransType(transType);
		trans.setTransAmount(amount);
		
		if(account !=null){
			
			synchronized(account){

				currBal = account.getBalance();
				trans.setPreBalance(currBal);
				trans.setPostBalance(currBal);
				
				if(transType.equals("Withdraw")){
					//Business Rule 1
					if(amount.compareTo(new BigDecimal(10000))  == 1){
						trans.setMessage("Invalid Transaction : Withdrawl amount is greater than $10000");
						return trans;
					}
					
					//Business Rule 2
					if(currBal.subtract(amount).compareTo(new BigDecimal(100)) == -1){
						trans.setMessage("Invalid Transaction : Withdrawl amount will make available balance below mandatory limit of $100");
						return trans;
					}
					
					//Business Rule 3
					if(currBal.multiply(new BigDecimal(0.9)).compareTo(amount) == -1){
						trans.setMessage("Invalid Transaction : Withdrawl amount is greater than 90% of current Balance");
						return trans;
					}
					
					BigDecimal postBal = currBal.subtract(amount);
					account.setBalance(postBal);
					myDB.updateAccount(account);
					trans.setMessage("Withdrawl of :$" + amount + " is successfull!!");
					trans.setPostBalance(postBal);
				}else{
					BigDecimal postBal = currBal.add(amount);
					account.setBalance(postBal);
					myDB.updateAccount(account);
					trans.setMessage("Deposit of :$" + amount + " is successfull!!");
					trans.setPostBalance(postBal);
				}
				
			}
		}else{
			trans.setMessage("Invalid AccountID");
		}
		
		return trans;
	}
	
	
	
	
	

}
