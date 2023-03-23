package com.accolite.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.accolite.miniDB.MyDB;
import com.accolite.model.Account;
import com.accolite.model.Transaction;
import com.accolite.service.AccountServiceImpl;

public class AccountServiceImplMockTest {
	
		@InjectMocks
	 	private AccountServiceImpl accountServiceImpl;
	 
	    @Mock
	    private MyDB myDB;
	    
	    @Before
	    public void setupMock() {
	        MockitoAnnotations.initMocks(this);
	    }
	    
	    /*
	     * Junit Test to validate Get Account By ID functionality
	     */
	    @Test
	    public void getAccountByIdTest() {
	    	// Arrange
	    	when(myDB.selectAccount(2000)).thenReturn(new Account(100));
	    	
	    	// Act
	    	Account retrievedAccount = accountServiceImpl.getAccountById(2000);
	    	
	    	// Assert
	    	assertEquals(new Integer(100), retrievedAccount.getUserID());
	    }
	    
	    /*
	     * Junit Test to validate account deposit functionality
	     */
	    @Test
	    public void depositTest() throws Exception {
	    	
	    	Account account = new Account();
	    	account.setAccountId(5000);
	    	account.setAccountType("Saving");
	    	account.setBalance(new BigDecimal(200));
	    	account.setUserID(1000);
	    	
	    	// Arrange
	    	when(myDB.selectAccount(5000)).thenReturn(account);
	    	
	    	Transaction trans = accountServiceImpl.withOrDepoTrans(new Integer(5000), "Deposit", new BigDecimal(50));
	    	
	    	assertEquals(new BigDecimal(250), trans.getPostBalance());
	    	
	    }
	    
	    /*
	     * Junit Test to validate account Withdraw functionality
	     */
	    @Test
	    public void withdrawalTest() throws Exception {
	    	
	    	Account account = new Account();
	    	account.setAccountId(5000);
	    	account.setAccountType("Saving");
	    	account.setBalance(new BigDecimal(200));
	    	account.setUserID(1000);
	    	
	    	// Arrange
	    	when(myDB.selectAccount(5000)).thenReturn(account);
	    	
	    	Transaction trans = accountServiceImpl.withOrDepoTrans(new Integer(5000), "Withdraw", new BigDecimal(50));
	    	
	    	assertEquals(new BigDecimal(150), trans.getPostBalance());
	    	
	    }
	    
	    /*
	     * Junit Test to validate account Withdraw Business Rule : Withdrawal amount cant be greater than $10000
	     */
	    @Test
	    public void withdrawalBusinessRule1Test() throws Exception {
	    	
	    	Account account = new Account();
	    	account.setAccountId(5000);
	    	account.setAccountType("Saving");
	    	account.setBalance(new BigDecimal(200));
	    	account.setUserID(1000);
	    	
	    	// Arrange
	    	when(myDB.selectAccount(5000)).thenReturn(account);
	    	
	    	Transaction trans = accountServiceImpl.withOrDepoTrans(new Integer(5000), "Withdraw", new BigDecimal(10001));
	    	
	    	assertEquals(new BigDecimal(200), trans.getPostBalance());
	    	
	    }
	    
	    /*
	     * Junit Test to validate account Withdraw Business Rule : Withdrawal amount should not make current balance below $100
	     */
	    @Test
	    public void withdrawalBusinessRule2Test() throws Exception {
	    	
	    	Account account = new Account();
	    	account.setAccountId(5000);
	    	account.setAccountType("Saving");
	    	account.setBalance(new BigDecimal(200));
	    	account.setUserID(1000);
	    	
	    	// Arrange
	    	when(myDB.selectAccount(5000)).thenReturn(account);
	    	
	    	Transaction trans = accountServiceImpl.withOrDepoTrans(new Integer(5000), "Withdraw", new BigDecimal(101));
	    	
	    	assertEquals(new BigDecimal(200), trans.getPostBalance());
	    	
	    }
	    
	    /*
	     * Junit Test to validate account Withdraw Business Rule : User cant withdraw more than 90% of current balance in single transaction.
	     */
	    @Test
	    public void withdrawalBusinessRule3Test() throws Exception {
	    	
	    	Account account = new Account();
	    	account.setAccountId(5000);
	    	account.setAccountType("Saving");
	    	account.setBalance(new BigDecimal(1000));
	    	account.setUserID(1000);
	    	
	    	// Arrange
	    	when(myDB.selectAccount(5000)).thenReturn(account);
	    	
	    	Transaction trans = accountServiceImpl.withOrDepoTrans(new Integer(5000), "Withdraw", new BigDecimal(901));
	    	
	    	assertEquals(new BigDecimal(1000), trans.getPostBalance());
	    	
	    }
	    

}
