package com.accolite.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.model.Account;
import com.accolite.model.Transaction;
import com.accolite.model.User;
import com.accolite.service.AccountService;
import com.accolite.service.UserService;
import com.accolite.validations.BankValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/account")
@Api(value="Account Operations", description="Account Endpoints")
public class AccountController {
	
    private AccountService accountService;
    

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
    
    /*
     * fetchAccountList is Mapping method executed when account/list/{userId} URL is being called
     * This will fetch list of accounts associated with given userId
     */
    @ApiOperation(value = "Search accounts by UserId")
    @RequestMapping(value = "/list/{userID}", method= RequestMethod.GET, produces = "application/json")
    public Iterable<Integer> fetchAccountList(@PathVariable Integer userID){
        Iterable<Integer> accountList = accountService.listAllAccounts(userID);  
        return accountList;
    }
    
    /*
     * showAccount is mapping method executed when account/{accountId} URL is being called
     * This will fetch account details corresponding to given accountId
     * This method will return Account object
     * In case given account Id is invalid, it will show message in Account Object in json
     */
    @ApiOperation(value = "Search a account with an AccountId",response = Account.class)
    @RequestMapping(value = "/{accountId}", method= RequestMethod.GET, produces = "application/json")
    public Account showAccount(@PathVariable Integer accountId){
    	Account account = accountService.getAccountById(accountId);
    	if(account !=null)
    		account.setMessage("Account Information Fetch Successfully!!");
    	else{
    		account = new Account();
    		account.setMessage("Invalid AccountId!!");
    	}
        return account;
    }
    
    /*
     * saveAccount method is used to create new Account of user with userId given in URL
     * This method will return newly created Account details.
     * In case given userID is invalid, it will show message in Account Object in json
     */
    @ApiOperation(value = "Create a account")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public Account saveAccount(@RequestParam Integer userID){
    	Account account = accountService.createAccount(userID);
    	if(account != null){
    		account.setMessage("New Account created with AccountId :" + account.getAccountId());
    	}else{
    		account = new Account();
    		account.setMessage("Invalid UserId");
    	}
        return account;
    }
    
    
    /*
     * updateAccount method is used to perform Withdraw or Deposit in given account
     * This method will return Transaction detail containing pre,post balance, transaction type 
     * In case transaction fails due to business rules, it will show HTTP status as 200 with exact error message in transaction body
     */
    @ApiOperation(value = "Deposit or Withdraw Transaction")
    @RequestMapping(value = "", method = RequestMethod.PATCH, produces = "application/json")
    public Transaction updateAccount(@RequestParam Integer accountId, @ApiParam(allowableValues ="Withdraw,Deposit") @RequestParam String transType, @RequestParam BigDecimal amount){
    	return accountService.withOrDepoTrans(accountId, transType, amount);
    }
    
    
    /*
     * deleteAccount method is used to delete account with accountId given in URL
     * This method will return message showing account deleted or not
     */
    @ApiOperation(value = "Delete a account")
    @RequestMapping(value="/delete/{accountId}", method = RequestMethod.DELETE)
    public String deleteAccount(@PathVariable Integer accountId){
    	String message = "";
    	Account account = accountService.deleteAccount(accountId);
    	if(account !=null)
    		message = "Account:" + accountId +" deleted successfully ";
    	else
    		message = "Invalid AccountId!!";
    	
    	return message;
    	

    }


	
}
