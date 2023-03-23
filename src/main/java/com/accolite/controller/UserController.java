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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user")
@Api(value="User Operations", description="User Endpoints")
public class UserController {
	
    private UserService userService;
    
    private BankValidator bankValidator;
    
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @Autowired
    public void setBankValidator(BankValidator bankValidator) {
        this.bankValidator = bankValidator;
    }
    
    /*
     * saveUser method is used to create new user with username given as Request Parameter
     * This method will return newly created USer details.
     */
    @ApiOperation(value = "Create a User")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> saveUser(@RequestParam String userName){
    	User user = null;
    	String message = bankValidator.userValidator(userName);
    	
    	if(("").equalsIgnoreCase(message)){
    		user = userService.createUser(userName);
    			if(user != null)
    				user.setMessage("New User created with UserId :" + user.getUserId());
    		return new ResponseEntity<User>(user, HttpStatus.CREATED);
    	}else{
    		user = new User();
    		user.setMessage(message);
    		return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
    	}
    }
    
    
    /*
     * getUser is mapping method executed when user/{userID} URL is being called
     * This will fetch user details corresponding to given userID
     * This method will return User details along with HttpStatus
     * In case given user Id is invalid, it will show message in User Object in json
     */
    @ApiOperation(value = "Fetch a User")
    @RequestMapping(value = "/{userID}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable Integer userID){
    	User user = userService.fetchUser(userID);
    	if(user != null)
    		user.setMessage("User Information Fetched Successfully");
    	else{
    		user = new User();
    		user.setMessage("User Not Found !!");
    	}
    	return new ResponseEntity<User>(user,HttpStatus.OK); 
    }
    

}
