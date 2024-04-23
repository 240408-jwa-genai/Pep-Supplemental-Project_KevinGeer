package com.revature.controller;

// import com.revature.MainDriver;
import com.revature.models.Moon;
import com.revature.service.MoonService;
// import com.revature.repository.MoonDao;  //Importing dao only for testing purposes.

// import com.revature.models.Planet;
import com.revature.repository.PlanetDao;
import com.revature.service.PlanetService;

import java.util.List;

public class MoonController {
	
	private MoonService moonService;
	public static PlanetDao planetDao = new PlanetDao();
    public static PlanetService planetService = new PlanetService(planetDao);

	public MoonController(MoonService moonService) {
		this.moonService = moonService;
	}

	public void getAllMoons(int currentUserId) {
		List<Moon> allMoons = moonService.getAllMoons(currentUserId);
		if(allMoons.size()==0){
			System.out.println("You have no moons.");
		}
		for(Moon moon : allMoons){
			System.out.print(moon.toString() + ", ");	
		}
	}

	public void getMoonByName(int currentUserId, String name) {
		Moon moon = moonService.getMoonByName(currentUserId, name);
		if(moon.getName()==null){
			System.out.println("You are not authorized to access this moon, or it does not exist.");
		}else{
			System.out.print(moon.toString() + ", ");
		}
	}

	public void getMoonById(int currentUserId, int id) {
		Moon moon = moonService.getMoonById(currentUserId, id);
		if(moon.getName()==null){
			System.out.println("You are not authorized to access this moon, or it does not exist.");
		}else{
			System.out.print(moon.toString() + ", ");
		}
	}

	public void createMoon(int currentUserId, Moon moon) {
		if(planetService.getPlanetById(currentUserId, moon.getMyPlanetId()).getName() == null){
			System.out.println("Cannot create a moon on a planet you don't own.");
		}else{
			Moon createdMoon = moonService.createMoon(moon);
			if(createdMoon.getName() == null){
				System.out.println("Failed to create the moon.");
			}else{
				System.out.println("You have successfully created the following moon!");
				System.out.print(createdMoon.toString());
			}
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
		if(allMoons.size()==0){
			System.out.println("No data. You might not have moons around this planet, the planet might not exist, or you don't have access to the planet.");
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
	// 	testmoon1.setName("KingReeveMoon1");
	// 	testmoon1.setMyPlanetId(5);

	// // 	//All retrievals work
	// // 	System.out.println(service.getAllMoons(1));
	// // 	System.out.println(service.getMoonById(1,5));
	// // 	System.out.println(service.getMoonByName(1, "gigachad"));
	// // 	System.out.println(service.getMoonsFromPlanet(2, 3));

	// // 	System.out.println(service.getAllMoons(900));
	// // 	System.out.println(service.getMoonById(900,5));
	// // 	System.out.println(service.getMoonByName(900, "gigachad"));
	// // 	System.out.println(service.getMoonsFromPlanet(900, 3));

	// 	//Create moons works, set to null check, since dao returns null IFF there is no match.
	// 	// System.out.println(service.createMoon(testmoon1));

	// // 	//Delete moon works
	// 	// System.out.println(service.deleteMoonById(3));
	// }
}
