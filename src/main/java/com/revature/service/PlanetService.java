package com.revature.service;

import java.util.List;

import com.revature.models.Planet;
import com.revature.repository.PlanetDao;

public class PlanetService {

	private PlanetDao dao;

	public PlanetService(PlanetDao dao){
		this.dao = dao;
	}

	public List<Planet> getAllPlanets() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Planet> getPlanetByName(int ownerId, String planetName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Planet getPlanetById(int ownerId, int planetId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Planet createPlanet(int ownerId, Planet planet) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deletePlanetById(int planetId) {
		// TODO Auto-generated method stub
		return false;
	}
	public static void main(String[] args){
		PlanetDao dao = new PlanetDao();
		//all planet related SQL works

		// Planet testplanet = new Planet();
		// testplanet.setId(1);
		// testplanet.setName("testingonetwo");
		// testplanet.setOwnerId(1);

		//getting planets works
		// System.out.println(dao.getAllPlanets().toString());
		// System.out.println(dao.getPlanetById(3));
		// System.out.println(dao.getPlanetByName("test name"));
		
		//creating planet works
		// dao.createPlanet(testplanet);
		
		//delete works
		// System.out.println(dao.getPlanetByName("testingonetwo"));
		// if(dao.deletePlanetById(4) == true){
		// 	System.out.println("true");
		// }
	}
}
