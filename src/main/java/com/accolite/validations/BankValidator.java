package com.accolite.validations;

import org.springframework.context.annotation.Configuration;

import com.accolite.model.User;

/*
 * This class contains all basic validations 
 */
@Configuration
public class BankValidator {
	
	public String userValidator(String userName){
		String message = ""; 
		
		if((userName == null || userName.equals("") || userName.toLowerCase().equals("string")))
			message = "Invalid UserName";
		
		return message;
	}

}
