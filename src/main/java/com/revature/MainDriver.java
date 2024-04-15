package com.revature;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.controller.UserController;
import com.revature.exceptions.UserFailException;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;
import com.revature.service.UserService;
import com.revature.utilities.ConnectionUtil;

public class MainDriver {
    public static UserDao userDao = new UserDao();
    public static UserService userService = new UserService(userDao);
    public static UserController userController = new UserController(userService);
    public static User currentUser;
    public static boolean loggedOn;
    public static void main(String[] args) {
        // TODO: implement main method to initialize layers and run the application

        try (Scanner scanner = new Scanner(System.in)){
            boolean userIsActive = true;



            while(userIsActive == true){
                System.out.println("\nHello! Welcome to the Planetarium!\n\nEnter 1 to register an account, 2 to log in.\n\nEnter 3 to quit.");
                int userChoice = Integer.parseInt(scanner.nextLine());
                if(userChoice == 1){
                    System.out.println("You chose to register an account.\nEnter desired username.");
                    String potentialUsername = scanner.nextLine();

                    System.out.println("Please enter your desired password.");
                    String potentialPassword = scanner.nextLine();
                    System.out.println(potentialUsername + " " + potentialPassword);
                    
                    User potentialUser = new User();
                    potentialUser.setUsername(potentialUsername);
                    potentialUser.setPassword(potentialPassword);

                    
                    userController.register(potentialUser);

                    // TODO: finish implementing user feedback for register operation


               }else if(userChoice ==2){
                    System.out.println("You chose to log in!\n\n");

                    System.out.println("Username:\n");
                    String attemptUsername = scanner.nextLine();

                    System.out.println("Password:\n");
                    String attemptPassword = scanner.nextLine();

                    UsernamePasswordAuthentication loginAttempt = new UsernamePasswordAuthentication();
                    loginAttempt.setUsername(attemptUsername);
                    loginAttempt.setPassword(attemptPassword);

                    userController.authenticate(loginAttempt);

               }else if(userChoice == 3){
                    System.out.println("Goodbye!");
                    userIsActive = false;
               }else{
                   System.out.println("Invalid option.\nYou need to choose 1, 2, or 3.");
                }
            //even if it crashes here, since scanner is a resource, it'll autoclose.
            }
        }
    }
}
