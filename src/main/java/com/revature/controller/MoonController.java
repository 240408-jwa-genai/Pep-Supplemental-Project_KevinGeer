package com.revature.controller;

// import com.revature.MainDriver;
import com.revature.models.Moon;
import com.revature.service.MoonService;
// import com.revature.repository.MoonDao;  //Importing dao only for testing purposes.

// import com.revature.models.Planet;
// import com.revature.service.PlanetService;

import java.util.List;

public class MoonController {
	
	private MoonService moonService;

	// private PlanetService planetService;

	public MoonController(MoonService moonService) {
		this.moonService = moonService;
	}

	public void getAllMoons(int currentUserId) {
		List<Moon> allMoons = moonService.getAllMoons(currentUserId);
		for(Moon moon : allMoons){
			System.out.print(moon.toString() + ", ");
		}
	}

	public void getMoonByName(int currentUserId, String name) {
		Moon moon = moonService.getMoonByName(currentUserId, name);
		System.out.print(moon.toString());
	}

	public void getMoonById(int currentUserId, int id) {
		Moon moon = moonService.getMoonById(currentUserId, id);
		System.out.print(moon.toString());
	}

	public void createMoon(Moon moon) {
		// TODO: Authenticate that the user has access to the planet they're creating the moon on first. I guess do it in the main driver.
		Moon createdMoon = moonService.createMoon(moon);
		if(createdMoon.getName() == null){
			System.out.println("Moon creation failed.");
		}else{
			System.out.println("You have successfully created the following moon!");
			System.out.print(createdMoon.toString());
		}
	}

	public void deleteMoon(int currentUserId, int id) {
		Moon authentication = moonService.getMoonById(currentUserId, id);
		if(authentication.getName() == null){
			System.out.println("You do not have access to this moon, or it doesn't exist.");
		}else{
			if(moonService.deleteMoonById(id)){
				System.out.println("Moon deletion successful.");
			}else{
				System.out.println("Unsuccessful deletion");
			}
		}
	}
	
	public void getPlanetMoons(int currentUserId, int myPlanetId) {
		// If currenUserId didn't create the planet you're getting these moons from, it'll return null
		List<Moon> allMoons = moonService.getMoonsFromPlanet(currentUserId, myPlanetId);
		if(allMoons == null){
			System.out.println("No data.");
		}else{
			for(Moon moon : allMoons){
				System.out.print(moon.toString() + ", ");
			}
		}
	}

	//little bit of hand-testing
	// public static void main(String[] args){
	// 	MoonDao dao = new MoonDao();
	// 	MoonService service = new MoonService(dao);

	// 	Moon testmoon1 = new Moon();
	// 	testmoon1.setName("Not a Gigachad");
	// 	testmoon1.setMyPlanetId(3);

	// 	//All retrievals work
	// 	System.out.println(service.getAllMoons(1));
	// 	System.out.println(service.getMoonById(1,5));
	// 	System.out.println(service.getMoonByName(1, "gigachad"));
	// 	System.out.println(service.getMoonsFromPlanet(2, 3));

	// 	System.out.println(service.getAllMoons(900));
	// 	System.out.println(service.getMoonById(900,5));
	// 	System.out.println(service.getMoonByName(900, "gigachad"));
	// 	System.out.println(service.getMoonsFromPlanet(900, 3));

	// 	//Create moons works, set to null check, since dao returns null IFF there is no match.
	// 	System.out.println(service.createMoon(testmoon1));

	// 	//Delete moon works
	// 	System.out.println(service.deleteMoonById(9));
	// }
}
