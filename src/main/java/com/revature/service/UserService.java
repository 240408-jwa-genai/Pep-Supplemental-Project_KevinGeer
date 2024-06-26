package com.revature.service;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;

public class UserService {

	private UserDao dao;

	public UserService(UserDao dao){
		this.dao = dao;
	}

	public User authenticate(UsernamePasswordAuthentication loginRequestData) {
		User compareUser = dao.getUserByUsername(loginRequestData.getUsername());
		
		if(compareUser != null){
			boolean passwordMatch = loginRequestData.getPassword().equals(compareUser.getPassword());
			if(passwordMatch){
				return compareUser;
			}
		}
		return new User();
	}

	public User register(User registerRequestData) {
		String username = registerRequestData.getUsername();
		if(username.equals(dao.getUserByUsername(username).getUsername())){
			System.out.println("\n\nThis username is already taken.  Try again!");
			return null;
		}
		if(registerRequestData.getUsername().length() <= 30 && registerRequestData.getPassword().length() <= 30){
			User possibleDuplicateUsername = dao.getUserByUsername(registerRequestData.getUsername()); 

			if(possibleDuplicateUsername != null){
				UsernamePasswordAuthentication validCredentials = new UsernamePasswordAuthentication();
				validCredentials.setUsername(registerRequestData.getUsername());
				validCredentials.setPassword(registerRequestData.getPassword());
				
				return dao.createUser(validCredentials);
				
			}
		}
		return null;
	}

	public User getUserById(int id){
		return dao.getUserById(id);
	}

	//  We do a little hand testing
	//  public static void main(String[] args){
	// 	UserDao dao = new UserDao();
	// 	User returnedUser = dao.getUserByUsername("test user");
	// 	System.out.println(returnedUser.getUsername());
		
	// 	UserDao dao = new UserDao();
	// 	UsernamePasswordAuthentication newCreds = new UsernamePasswordAuthentication();
	// 	newCreds.setUsername("newuser");
	// 	newCreds.setPassword("newpassword");
	// 	User returnedUser = dao.createUser(newCreds);
	// 	System.out.println(returnedUser.getUsername());
		
	// 	System.out.println(dao.getUserById(3).getUsername());
	// }
}