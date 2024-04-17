package com.revature.service;

import java.util.List;

import com.revature.models.Planet;
import com.revature.repository.PlanetDao;

public class PlanetService {

	private PlanetDao dao;

	public PlanetService(PlanetDao dao){
		this.dao = dao;
	}

	public List<Planet> getAllPlanets(int ownerId) {
		return dao.getAllPlanets(ownerId);
	}

	public Planet getPlanetByName(int ownerId, String planetName) {
		return dao.getPlanetByName(ownerId, planetName);
	}

	public Planet getPlanetById(int ownerId, int planetId) {
		return dao.getPlanetById(ownerId, planetId);
	}

	public Planet createPlanet(int ownerId, String planetName) {
		if(dao.getAnyPlanetByName(planetName).getName() != null){
			System.out.println("Cannot create a planet with a duplicate name.");
			return null;
		}else{
			System.out.println("Successful creation of planet.");
			Planet createdPlanet = new Planet();
			createdPlanet.setName(planetName);
			createdPlanet.setOwnerId(ownerId);
			return dao.createPlanet(createdPlanet);
		}
	}

	public Boolean deletePlanetById(int ownerId, int planetId) {
		if(dao.getPlanetById(ownerId, planetId).getName() != null){
			if(dao.deletePlanetById(planetId)){
				System.out.println("Deletion of planet successful.");
				return true;
			}else{
				System.out.println("Deletion of planet unsuccessful.");
				return false;
			}
		}else{
			System.out.println("You do not have access, or planet does not exist.");
			return false;
		}
	}
	// public static void main(String[] args){
	// 	PlanetDao dao = new PlanetDao();
	// 	//all planet related SQL works

	// 	Planet testplanet = new Planet();
	// 	testplanet.setId(1);
	// 	testplanet.setName("testingonetwo");
	// 	testplanet.setOwnerId(1);

	// 	//getting planets works
	// 	System.out.println(dao.getAllPlanets().toString());
	// 	System.out.println(dao.getPlanetById(3));
	// 	System.out.println(dao.getPlanetByName("test name"));
		
	// 	//creating planet works
	// 	dao.createPlanet(testplanet);
		
	// 	//delete works
	// 	System.out.println(dao.getPlanetByName("testingonetwo"));
	// 	if(dao.deletePlanetById(4) == true){
	// 		System.out.println("true");
	// 	}
	// }
}
