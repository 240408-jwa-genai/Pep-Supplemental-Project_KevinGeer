package com.revature.service;

import java.util.List;

import com.revature.models.Moon;
import com.revature.repository.MoonDao;

public class MoonService {

	private MoonDao dao;

	public MoonService(MoonDao dao) {
		this.dao = dao;
	}

	public List<Moon> getAllMoons(int ownerId) {
		// TODO implement
		return dao.getAllMoons(ownerId);
	}

	public Moon getMoonByName(int userId, String moonName) {
		// TODO implement
		return dao.getMoonByName(userId, moonName);
	}

	public Moon getMoonById(int myPlanetId, int moonId) {
		// TODO Aimplement
		return null;
	}

	public Moon createMoon(Moon m) {
		// TODO implement
		return null;
	}

	public boolean deleteMoonById(int moonId) {
		return false;
	}

	public List<Moon> getMoonsFromPlanet(int myPlanetId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args){
		MoonDao dao = new MoonDao();
		
		//retrievals all work
		System.out.println(dao.getAllMoons(1).toString());
		System.out.println(dao.getMoonById(1, 5));
		System.out.println(dao.getMoonByName(2, "gigachad"));
		System.out.println(dao.getMoonsFromPlanet(3).toString());

		System.out.println(dao.getMoonById(0, 0));
		
		//createmoon works
		// Moon testMoon = new Moon();
		// testMoon.setName("moonservicetest");
		// testMoon.setMyPlanetId(2);
		// System.out.println(dao.createMoon(testMoon));

		//deleteMoon works
		// System.out.println(dao.deleteMoonById(8));
	}
}
