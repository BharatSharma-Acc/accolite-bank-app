package com.accolite.model;

import io.swagger.annotations.ApiModelProperty;

//import javax.persistence.*;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Account implements Serializable{

	private static AtomicInteger ID_GENERATOR = new AtomicInteger(50000);
	
	public Account() {
		super();
	}

	public Account(Integer userID) {
		super();
		this.accountId = ID_GENERATOR.getAndIncrement();
		this.accountType = "Saving";
		this.balance = new BigDecimal(0);
		this.userID = userID;
	}


	@ApiModelProperty(notes = "Account ID")
    private Integer accountId;

    @ApiModelProperty(notes = "Account Type")
    private String accountType;

    @ApiModelProperty(notes = "The balance in account", required = true)
    private BigDecimal balance;
    
    @ApiModelProperty(notes = "Message")
    private String message;
    
    private Integer userID;


	public Integer getAccountId() {
		return accountId;
	}


	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	

	public Integer getUserID() {
		return userID;
	}


	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
