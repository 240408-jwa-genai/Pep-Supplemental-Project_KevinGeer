package com.revature.service;

import java.util.List;

import com.revature.models.Moon;
import com.revature.repository.MoonDao;

public class MoonService {

	private MoonDao dao;

	public MoonService(MoonDao dao) {
		this.dao = dao;
	}

	public List<Moon> getAllMoons() {
		// TODO implement
		return dao.getAllMoons();
	}

	public Moon getMoonByName(int myPlanetId, String moonName) {
		// TODO implement
		return dao.getMoonByName(myPlanetId, moonName);
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
		System.out.println(dao.getAllMoons().toString());
	}
}
