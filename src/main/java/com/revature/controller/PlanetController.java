package com.revature.controller;

import java.util.List;

import com.revature.models.Planet;
// import com.revature.repository.PlanetDao; // testing
import com.revature.service.PlanetService;

public class PlanetController {
	
	private PlanetService planetService;

	public PlanetController(PlanetService planetService){
		this.planetService = planetService;
	}

	public void getAllPlanets(int currentUserId) {
		List<Planet> allPlanets = planetService.getAllPlanets(currentUserId);
		for(Planet planet : allPlanets){
			System.out.print(planet.toString() + ", ");
		}
	}

	public void getPlanetByName(int currentUserId, String name) {
		Planet planet = planetService.getPlanetByName(currentUserId, name);
		System.out.print(planet.toString());
	}

	public void getPlanetByID(int currentUserId, int id) {
		Planet planet = planetService.getPlanetById(currentUserId, id);
		System.out.print(planet.toString());
	}

	public void createPlanet(int currentUserId, String planetName) {
		planetService.createPlanet(currentUserId, planetName);
	}

	public void deletePlanet(int currentUserId, int id) {
		planetService.deletePlanetById(currentUserId, id);
	}

		//little bit of hand-testing
	// public static void main(String[] args){
	// 	PlanetDao dao = new PlanetDao();
	// 	PlanetService service = new PlanetService(dao);

		// Planet testplanet1 = new Planet();
		// testplanet1.setName("Not a Gigachad");
		// testplanet1.setOwnerId(3);

		// //All retrievals work
		// System.out.println(service.getAllPlanets(1));
		// System.out.println(service.getPlanetById(1,1));
		// System.out.println(service.getPlanetByName(2, "monstah"));
		
		// System.out.println(service.getAllPlanets(900));
		// System.out.println(service.getPlanetById(900,5));
		// System.out.println(service.getPlanetByName(900, "monstah"));

		// //Create planets works, set to null check, since dao returns null IFF there is no match.
		// System.out.println(service.createPlanet(2, "CreamGetTheMoney"));
		// System.out.println(service.createPlanet(3, "Ehlana"));

		//Delete planet works
		// System.out.println(service.deletePlanetById(2,1));
	// }


}
