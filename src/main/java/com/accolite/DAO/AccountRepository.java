package com.accolite.DAO;

import java.math.BigDecimal;

import com.accolite.model.Account;
import com.accolite.model.User;

// This is Dummy Interface to be used in Future when In-memory data 
// will be replaced with Actual DB

public interface AccountRepository {
	
	public Account fetchAccount(Integer acctID);
	public Account createAccount(Integer userID);
	public void withdraworDepositAmountByAcctID(Integer acctID, BigDecimal balance, String transType);
	
}
