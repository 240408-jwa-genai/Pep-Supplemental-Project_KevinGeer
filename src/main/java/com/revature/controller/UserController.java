package com.revature.controller;

import static com.revature.MainDriver.currentUser;
import com.revature.exceptions.UserFailException;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;
import com.revature.service.UserService;


public class UserController {
	private static UserDao userDao = new UserDao();
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	public void authenticate(UsernamePasswordAuthentication loginRequestData) throws UserFailException{
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
		User userResponse = userService.register(registerRequestData);
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

	public void deleteAccount(int id){
		if(userDao.deleteAccount(id)){
			System.out.println("Your account has been deleted.");
		}else{
			System.out.println("Account deletion failed.");
		}
	}
}