package com.accolite.service;

import java.math.BigDecimal;

import com.accolite.model.Account;
import com.accolite.model.Transaction;
import com.accolite.model.User;

public interface AccountService {
	
    Iterable<Integer> listAllAccounts(Integer userID);

    Account getAccountById(Integer id);

    Account createAccount(Integer userID);
    
    Account deleteAccount(Integer id);
    
    Transaction withOrDepoTrans(Integer accountId, String transType, BigDecimal amount);

}
