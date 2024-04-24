// package com.revature;

// // import java.io.IOException;
// // import java.sql.SQLException;
// import java.util.Scanner;

// import com.revature.controller.MoonController;
// import com.revature.controller.PlanetController;
// import com.revature.controller.UserController;
// import com.revature.models.Moon;
// // import com.revature.exceptions.UserFailException;
// import com.revature.models.User;
// import com.revature.models.UsernamePasswordAuthentication;

// import com.revature.repository.MoonDao;
// import com.revature.repository.PlanetDao;
// import com.revature.repository.UserDao;

// import com.revature.service.PlanetService;
// import com.revature.service.UserService;
// import com.revature.service.MoonService;
// // import com.revature.utilities.ConnectionUtil;

// public class TestDriver {
//     public static UserDao userDao = new UserDao();
//     public static UserService userService = new UserService(userDao);
//     public static UserController userController = new UserController(userService);
//     public static User currentUser;

//     public static PlanetDao planetDao = new PlanetDao();
//     public static PlanetService planetService = new PlanetService(planetDao);
//     public static PlanetController planetController = new PlanetController(planetService);
    
//     public static MoonDao moonDao = new MoonDao();
//     public static MoonService moonService = new MoonService(moonDao);
//     public static MoonController moonController = new MoonController(moonService);
    
//     public static Scanner scanner;

//     public static void main(String[] args) {
//         // System.out.println("Please input the name you'd like to give your new moon. Input 'Q' to return.\n");
//         // String inputCaseTwo = "KingReeveMoon1";
//         // Moon moon = new Moon();
//         // moon.setMyPlanetId(3);
//         // moon.setName(inputCaseTwo);
//         // System.out.println(moon.getId() + moon.getName() + moon.getMyPlanetId());
//         // moonController.createMoon(currentUser.getId(), moon);
//         // moonController.createMoon(2, moon);

//         // System.out.println(planetService.getPlanetById(2, 3));
//     }
    
// }
