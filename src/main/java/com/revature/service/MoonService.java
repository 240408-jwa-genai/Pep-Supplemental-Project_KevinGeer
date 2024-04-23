package com.revature.service;

import java.util.List;

// import com.revature.MainDriver;
import com.revature.models.Moon;
import com.revature.repository.MoonDao;

public class MoonService {

	private MoonDao dao;

	public MoonService(MoonDao dao) {
		this.dao = dao;
	}

	public List<Moon> getAllMoons(int userId) {
		return dao.getAllMoons(userId);
	}

	public Moon getMoonByName(int userId, String moonName) {
		return dao.getMoonByName(userId, moonName);
	}

	public Moon getMoonById(int userId, int moonId) {
		return dao.getMoonById(userId, moonId);
	}

	public Moon createMoon(Moon moon) {

		if(dao.getAnyMoonByName(moon.getName()).getName() != null){   //&& dao.getAnyMoonByName(moon.getName()).getName().equals(moon.getName())
			System.out.println("Cannot create a moon with a duplicate name.");
			moon.setName(null);
			return moon;
		}else{
			return dao.createMoon(moon);
		}
		
	}

	public boolean deleteMoonById(int moonId) {
		Moon moon = dao.getMoonByIdNoCheck(moonId);
		if(dao.deleteMoonById(moonId) == true){
			System.out.println(moon.toString());
		}
		return dao.deleteMoonById(moonId);
	}

	public List<Moon> getMoonsFromPlanet(int currentUserId, int myPlanetId) {
		return dao.getMoonsFromPlanet(currentUserId, myPlanetId);
	}



	//	Testing hands again
	// public static void main(String[] args){
	// 	MoonDao dao = new MoonDao();
		
	// 	// retrievals all work
	// 	System.out.println(dao.getAllMoons(1).toString());
	// 	System.out.println(dao.getMoonById(1, 5));
	// 	System.out.println(dao.getMoonByName(2, "gigachad"));
	// 	System.out.println(dao.getMoonsFromPlanet(1, 3).toString());

	// 	System.out.println(dao.getMoonById(0, 0));
		
	// 	// createmoon works
	// 	Moon testMoon = new Moon();
	// 	testMoon.setName("moonservicetest");
	// 	testMoon.setMyPlanetId(2);
	// 	System.out.println(dao.createMoon(testMoon));

	// 	// deleteMoon works
	// 	System.out.println(dao.deleteMoonById(8));
	// }
}
