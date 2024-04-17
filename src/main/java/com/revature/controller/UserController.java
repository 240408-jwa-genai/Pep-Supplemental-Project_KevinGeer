package com.revature.controller;

import static com.revature.MainDriver.currentUser;
import com.revature.exceptions.UserFailException;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.service.UserService;


public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	public void authenticate(UsernamePasswordAuthentication loginRequestData) throws UserFailException{
		// if(loginRequestData != null){
		// 	User authenticated = userService.authenticate(loginRequestData);
		// 	if(authenticated != null){
		// 		System.out.println("Successfully logged in.\n");
		// 	}else{
		// 		throw new UserFailException("Login Failed");
		// 	}
		// }
		User possibleUser = userService.authenticate(loginRequestData);
		if (possibleUser.getId() != 0){
			//currentUser static import from MainDriver
			currentUser = userService.getUserById(possibleUser.getId()); 
			System.out.println(String.format("Hello %s! Welcome to the Planetarium\n", possibleUser.getUsername()));
		}else{
			System.out.println("Username/Password invalid.");
		}
		

	}

	public void register(User registerRequestData) {
		// 2 software requirements to check in this method: unique username, username&password <=30 characters.
		//Controller is only responisble for getting user input and returning messages/data to user.  Only send data to service layer, depending on response tell user success or not.	
		User userResponse = userService.register(registerRequestData);
		//Here be the conditionals
		if(userResponse == null){
			System.out.println("Registration failed, double check and try again.");
		}else{
			System.out.println("Registration successful.");
		}
	}

	public void logout() {
		System.out.printf("Goodbye, %s.", currentUser.getUsername());
		currentUser = null;
	}
	
	public boolean checkAuthorization(int userId, int ownderId) {	
		if(userId == ownderId){
			return true;
		}
		return false;
	}
}
