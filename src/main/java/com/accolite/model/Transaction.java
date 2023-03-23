package com.accolite.model;

import io.swagger.annotations.ApiModelProperty;

//import javax.persistence.*;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Transaction implements Serializable{
	
	
	public Transaction() {
		super();
	}

	@ApiModelProperty(notes = "Account ID")
    private Integer accountID;
	
    @ApiModelProperty(notes = "Transaction Type : Withdraw or Deposit")
    private String transType;

    @ApiModelProperty(notes = "Account to be deposit or Withdraw")
    private BigDecimal transAmount;
    
    @ApiModelProperty(notes = "Account Balance Before Transaction")
    private BigDecimal preBalance;
    
    @ApiModelProperty(notes = "Account Balance After Transaction")
    private BigDecimal postBalance;
    
    @ApiModelProperty(notes = "Success or Failure Message")
    private String message;

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public BigDecimal getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}

	public BigDecimal getPreBalance() {
		return preBalance;
	}

	public void setPreBalance(BigDecimal preBalance) {
		this.preBalance = preBalance;
	}

	public BigDecimal getPostBalance() {
		return postBalance;
	}

	public void setPostBalance(BigDecimal postBalance) {
		this.postBalance = postBalance;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Transaction [accountID=" + accountID + ", transType=" + transType + ", transAmount=" + transAmount
				+ ", preBalance=" + preBalance + ", postBalance=" + postBalance + ", message=" + message + "]";
	}

}
