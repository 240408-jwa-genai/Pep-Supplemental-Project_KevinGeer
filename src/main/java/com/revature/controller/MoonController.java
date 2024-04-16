package com.revature.controller;

import com.revature.MainDriver;
import com.revature.models.Moon;
import com.revature.service.MoonService;

import java.util.List;

public class MoonController {
	
	private MoonService moonService;

	public MoonController(MoonService moonService) {
		this.moonService = moonService;
	}

	public void getAllMoons(int currentUserId) {
		// TODO: implement
		List<Moon> allMoons = moonService.getAllMoons(currentUserId);
		for(Moon moon : allMoons){
			System.out.print(moon.toString() + ", ");
		}
	}

	public void getMoonByName(int currentUserId,String name) {
		// TODO: implement
		Moon moon = moonService.getMoonByName(currentUserId, name);
		System.out.print(moon.toString());
	}

	public void getMoonById(int currentUserId, int id) {
		// TODO: implement
		Moon moon = moonService.getMoonById(currentUserId, id);
		System.out.print(moon.toString());
	}

	public void createMoon(Moon moon) {
		// TODO: implement
		Moon createdMoon = moonService.createMoon(moon);
		System.out.print(createdMoon.toString());
	}

	public void deleteMoon(int currentUserId, int id) {
		// TODO: implement
		Moon authentication = moonService.getMoonById(currentUserId, id);
		if(authentication.getName() == null){
			System.out.println("You do not have access to this moon");
		}else{
			System.out.print(moonService.deleteMoonById(id));
		}
	}
	
	public void getPlanetMoons(int currentUserId, int myPlanetId) {
		// TODO: implement
		List<Moon> allMoons = moonService.getMoonsFromPlanet(myPlanetId);

		for(Moon moon : allMoons){
			System.out.print(moon.toString() + ", ");
		}
	}
}
