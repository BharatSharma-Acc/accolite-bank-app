package com.accolite.model;

import io.swagger.annotations.ApiModelProperty;

//import javax.persistence.*;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class User implements Serializable{
	
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(10000);
	
	public User() {
		super();
	}
	
	public User(String userName) {
		super();
		this.userName = userName;
		this.userId = ID_GENERATOR.getAndIncrement();
	}

	@ApiModelProperty(notes = "User ID")
    private Integer userId;

    @ApiModelProperty(notes = "user Name", required = true)
    private String userName;

    @ApiModelProperty(notes = "List of AccountId")
    private List<Integer> accounts;
    
    @ApiModelProperty(notes = "Message")
    private String message;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Integer> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Integer> accounts) {
		this.accounts = accounts;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
