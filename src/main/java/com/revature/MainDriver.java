package com.revature;

// import java.io.IOException;
// import java.sql.SQLException;
import java.util.Scanner;

import com.revature.controller.MoonController;
import com.revature.controller.PlanetController;
import com.revature.controller.UserController;
import com.revature.models.Moon;
// import com.revature.exceptions.UserFailException;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;

import com.revature.repository.MoonDao;
import com.revature.repository.PlanetDao;
import com.revature.repository.UserDao;

import com.revature.service.PlanetService;
import com.revature.service.UserService;
import com.revature.service.MoonService;
// import com.revature.utilities.ConnectionUtil;

public class MainDriver {
    public static UserDao userDao = new UserDao();
    public static UserService userService = new UserService(userDao);
    public static UserController userController = new UserController(userService);
    public static User currentUser;

    public static PlanetDao planetDao = new PlanetDao();
    public static PlanetService planetService = new PlanetService(planetDao);
    public static PlanetController planetController = new PlanetController(planetService);
    
    public static MoonDao moonDao = new MoonDao();
    public static MoonService moonService = new MoonService(moonDao);
    public static MoonController moonController = new MoonController(moonService);
    
    public static Scanner scanner;
    public static void main(String[] args) {
        // TODO: implement main method to initialize layers and run the application
        scanner = new Scanner(System.in);

        boolean anonymousUser = true;
        while(anonymousUser == true){
            System.out.println("\nHello! Welcome to the Planetarium!\n1 to register an account.\n2 to log in.\nEnter 3 to quit.");
            String errorCheck = scanner.nextLine();
            if(allDigits(errorCheck) != true){
                System.out.println("You must input an integer.");
            }else{
            int userChoice = Integer.parseInt(errorCheck);
            if(userChoice == 1){
                System.out.println("You chose to register an account.\nBoth username and password must be less than 30 characters.\n"+ 
                                    "The username must be unique.\nEnter desired username or enter 'Exit' to exit the program.");
                String potentialUsername = scanner.nextLine();
                if(potentialUsername.equalsIgnoreCase("Exit")){
                    System.out.println("You've chosen to exit.  Goodbye!");
                    break;
                }

                System.out.println("Please enter your desired password.");
                String potentialPassword = scanner.nextLine();
                System.out.println(potentialUsername + " " + potentialPassword);

                User potentialUser = new User();
                potentialUser.setUsername(potentialUsername);
                potentialUser.setPassword(potentialPassword);

                userController.register(potentialUser);

               }else if(userChoice == 2){
                    System.out.println("You chose to log in!\n\n");

                    System.out.println("Username:\n");
                    String attemptUsername = scanner.nextLine();

                    System.out.println("Password:\n");
                    String attemptPassword = scanner.nextLine();

                    UsernamePasswordAuthentication loginAttempt = new UsernamePasswordAuthentication();
                    loginAttempt.setUsername(attemptUsername);
                    loginAttempt.setPassword(attemptPassword);

                    userController.authenticate(loginAttempt);
                    
                    // break out of while loop
                    if(currentUser != null){
                        anonymousUser = false;
                        break;
                    }

               }else if(userChoice == 3){
                    System.out.println("Goodbye!");
                    anonymousUser = false;

               }else{
                   System.out.println("Invalid option.\nYou need to choose 1, 2, or 3.");
                }
            }
        }

        while(currentUser != null){
            //For creation operations, use currentUser.getId() to autopopulate the ownerId or myPlanetId (check they have access to that planet)
            System.out.printf("\n%s, Press 1 to manage your data. \nPress 2 to logout.\n\n", currentUser.getUsername());
            String errorCheck = scanner.nextLine();
            if(allDigits(errorCheck) != true){
                System.out.println("Input must be an integer");
            }else{
                int loggedOnChoice = Integer.parseInt(errorCheck);
                if(loggedOnChoice == 1){
                    System.out.printf("\n1 to manage your planets\n2 to manage your moons\nAnything else to return to main menu.\n");
                    String errorCheck2 = scanner.nextLine();
                    if(allDigits(errorCheck2) != true){
                        System.out.println("Input must be an integer"); 
                    }else{
                        int userManageData = Integer.parseInt(errorCheck2);
                        switch(userManageData){
                            case 1:
                                nestedPlanets();
                                break;
                            case 2:
                                nestedMoons();
                                break;
                            default:
                                System.out.println("Return to main menu.\n");
                                break;
                        } 
                    }                      
                }else if(loggedOnChoice == 2){
                    userController.logout();
                }else{
                    System.out.println("Please press 1 or 2\n");
                }
            }
        }
    scanner.close();
    }

    private static void nestedPlanets() {
        System.out.println("\n1 to search your planets\n2 to add a planet\n3 to delete a planet.\n");
        String errorCheck = scanner.nextLine();
        if(allDigits(errorCheck) != true){
            System.out.println("You must input an integer.");
        }else{
            int userPlanetInput = Integer.parseInt(errorCheck);
            switch(userPlanetInput){
                case 1:
                    planetSearch();
                    break;
                case 2:
                    System.out.println("Please input the name you'd like to give your new planet. Input 'Q' to return.\n");
                    String inputCaseTwo = scanner.nextLine();
                    if(allDigits(inputCaseTwo)){
                        System.out.println("\nQuitting to previous menu due to only numbered inputs.\n");
                        break;
                    }else if(inputCaseTwo.equalsIgnoreCase("Q")){
                        System.out.println("\nQuitting to previous menu due to entering 'Q'.");
                        break;
                    }else{
                        planetController.createPlanet(currentUser.getId(), inputCaseTwo);
                        break;
                    }             
                case 3:
                    System.out.println("Please input the ID of the planet you want to delete.  Input 'Q' to return.\n");
                    String inputCaseThree = scanner.nextLine();
                    if(!allDigits(inputCaseThree)){
                        System.out.println("\nQuitting to previous menu.");
                        break;
                    }else{
                        planetController.deletePlanet(currentUser.getId(), Integer.parseInt(inputCaseThree));
                        break;
                    }
                default:
                    System.out.println("\nInvalid input. Returning to previous menu.");
                    break;
            }
        }
    }


    private static void planetSearch(){
        System.out.println("1 to see all of your planets\n2 to search by name\n3 to search by ID");
        String userInput = scanner.nextLine();
        int userPlanetInput;
        if(allDigits(userInput) == true){
            userPlanetInput = Integer.parseInt(userInput);
            switch(userPlanetInput){
                case 1:
                    planetController.getAllPlanets(currentUser.getId());
                    break;
                case 2:
                    System.out.println("To search by name, please insert the name:\n");
                    planetController.getPlanetByName(currentUser.getId(),scanner.nextLine());
                    break;
                case 3:
                    System.out.println("To search by ID, please insert the ID:");
                    String caseThreeInput = scanner.nextLine();
                    if(allDigits(caseThreeInput) != true){
                        System.out.println("You must enter an integer for the ID");
                    }else{
                        planetController.getPlanetByID(currentUser.getId(), Integer.parseInt(caseThreeInput));
                    }
                    break;
                default:
                    System.out.println("Invalid input, returning to previous menu.");
                    break;
            }
        }else{
            System.out.println("You must input a number.");
        }        
    }

    private static void nestedMoons() {
        System.out.println("\n1 to search your moons\n2 to add a moon\n3 to delete a moon.\n");
        String errorCheck = scanner.nextLine();
        if(allDigits(errorCheck) != true){
            System.out.println("You must enter an integer.");
        }else{
            int userMoonInput = Integer.parseInt(errorCheck);
            switch(userMoonInput){
                case 1:
                    moonSearch();
                    break;
                case 2:
                    System.out.println("Please input the name you'd like to give your new moon. Input 'Q' to return.\n");
                    String inputCaseTwo = scanner.nextLine();
                    if(allDigits(inputCaseTwo)){
                        System.out.println("\nQuitting to previous menu due to only numbered inputs.\n");
                        break;
                    }else if(inputCaseTwo.equalsIgnoreCase("Q")){
                        System.out.println("\nQuitting to previous menu due to entering 'Q'.");
                        break;
                    }else{
                        System.out.println("Please input the ID of the planet on which you want to place this moon.");
                        String planetIdErrorCheck = scanner.nextLine();
                        if(allDigits(planetIdErrorCheck) != true){
                            System.out.println("The planet ID must be an integer.");
                            break;
                        }else{
                            Moon moon = new Moon();
                            moon.setMyPlanetId(Integer.parseInt(planetIdErrorCheck));
                            moon.setName(inputCaseTwo);
                            moonController.createMoon(currentUser.getId(), moon);
                            break;
                        }
                    }             
                case 3:
                    System.out.println("Please input the ID of the moon you want to delete.  Input 'Q' to return.\n");
                    String inputCaseThree = scanner.nextLine();
                    if(!allDigits(inputCaseThree)){
                        System.out.println("\nQuitting to previous menu.");
                        break;
                    }else{
                        moonController.deleteMoon(currentUser.getId(), Integer.parseInt(inputCaseThree));
                        break;
                    }
                default:
                    System.out.println("\nInvalid input. Returning to previous menu.");
                    break;
            }
        }
    }

    private static void moonSearch(){
        System.out.println("1 to see all of your moons\n2 to search by name\n3 to search by ID\n4 to see all moons of a specifc planet.");
        String userInput = scanner.nextLine();
        int userMoonInput;
        if(allDigits(userInput) == true){
            userMoonInput = Integer.parseInt(userInput);
            switch(userMoonInput){
                case 1:
                    moonController.getAllMoons(currentUser.getId());
                    break;
                case 2:
                    System.out.println("To search by name, please insert the name:\n");
                    moonController.getMoonByName(currentUser.getId(),scanner.nextLine());
                    break;
                case 3:
                    System.out.println("To search by ID, please insert the ID:");
                    String caseThreeInput = scanner.nextLine();
                    if(allDigits(caseThreeInput) != true){
                        System.out.println("You must enter an integer for the ID");
                    }else{
                        moonController.getMoonById(currentUser.getId(), Integer.parseInt(caseThreeInput));
                    }
                    break;
                case 4:            
                    System.out.println("Input the ID of the planet for which you want to see the moons.");
                    String caseFourInput = scanner.nextLine();
                    if(!allDigits(caseFourInput)){
                        System.out.println("You must enter an integer for the ID");
                    }else{                        
                        moonController.getPlanetMoons(currentUser.getId(), Integer.parseInt(caseFourInput));
                    }
                    break;
                default:
                    System.out.println("Invalid input, returning to previous menu.");
                    break;
            }
        }else{
            System.out.println("You must input a number.");
        }        
    }


    private static boolean allDigits(String n){
        boolean allDigits = true;
        if (!n.isEmpty()) {
            for (int i = 0; i < n.length(); i++) {
                if (!Character.isDigit(n.charAt(i))) {
                    allDigits = false;
                    break;
                }
            }
        }
        return allDigits;
    }
}