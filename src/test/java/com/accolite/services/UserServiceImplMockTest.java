package com.accolite.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.accolite.miniDB.MyDB;
import com.accolite.model.User;
import com.accolite.service.UserServiceImpl;

public class UserServiceImplMockTest {
	
	 private UserServiceImpl userServiceImpl;
	 
	    @Mock
	    private MyDB myDB;
	    
	    @Mock
	    private User user;
	    
	    @Before
	    public void setupMock() {
	        MockitoAnnotations.initMocks(this);
	        userServiceImpl=new UserServiceImpl();
	        userServiceImpl.setMyDB(myDB);
	    }
	    @Test
	    public void shouldReturnUser_whenFetchUserCalled() throws Exception {
	    	// Arrange
	    	
	    	when(myDB.selectUser(2000)).thenReturn(user);
	        // Act
	        User retrievedUser = userServiceImpl.fetchUser(2000);
	        // Assert
	        assertThat(retrievedUser, is(equalTo(user)));
	        
	    }
	    
}
