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

		//This will fail if the username isn't found, so don't need to check if the username matches.
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
		if(registerRequestData.getUsername().length() <= 30 && registerRequestData.getPassword().length() <= 30){
			User possibleDuplicateUsername = dao.getUserByUsername(registerRequestData.getUsername()); // I'm thinking this returns a null if it doesn't exist, and that's the only time you can register

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

	public static void main(String[] args){
		UserDao dao = new UserDao();
		// User returnedUser = dao.getUserByUsername("test user");
		// System.out.println(returnedUser.getUsername());
		
		// UserDao dao = new UserDao();
		// UsernamePasswordAuthentication newCreds = new UsernamePasswordAuthentication();
		// newCreds.setUsername("newuser");
		// newCreds.setPassword("newpassword");
		// User returnedUser = dao.createUser(newCreds);
		// System.out.println(returnedUser.getUsername());
		
		// System.out.println(dao.getUserById(3).getUsername());
	}
}