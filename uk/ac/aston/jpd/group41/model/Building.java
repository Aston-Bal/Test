package uk.ac.aston.jpd.group41.model;

import java.util.ArrayList;
import java.util.List;

import uk.ac.aston.jpd.group41.people.Person;
import  uk.ac.aston.jpd.group41.model.Floor;

/**
 * Represents the building containing the floors and the lifts {@code Building}}
 * 
 * @author Nishika
 * @version 2.3.0
 * @since JDK 11
 */

public class Building {

	private Floor[] floors;
	private Lift[] lifts;
	private List<Person> peopleInBuilding = new ArrayList<>();
	private int currentFloorOfLift;

	
	/**
	 * Creates a building with specific number of floors and lifts 
	 * 
	 * @param numFloors is an integer representing the number of floors to be created in the Building
	 * @param numLifts is an integer representing the number of lifts to be created in the Building
	 */
	public Building(int numFloors, int numLifts, Simulation simulation) {
		floors = new Floor[numFloors];
		lifts = new Lift[numLifts];
		for (int i = 0; i < numFloors; i++) {
			floors[i] = new Floor(i);
		}
		for (int i = 0; i < numLifts; i++) {
			lifts[i] = new Lift(4, numFloors-1, simulation);
		}
	}

	
	/**
	 * Adds a person to the queue waiting for the elevator on the ground floor of
	 * the building. This signifies the person just entered the building.
	 * 
	 * @param p represents the person who entered the building
	 */
	public void arrive(Person p) {
		peopleInBuilding.add(p);
		floors[0].moveToQueue(p);
	}

	
	/**
	 * Removes a person from the building
	 * 
	 * @param p represents the person who entered the building
	 */
	public void leave(Person p) {
		peopleInBuilding.remove(p);
		floors[0].removePerson(p);
	}

	
	/**
	 * Transfers people from the lift to floor when the lift has reached
	 */
	private void transferToLiftFromFloor() {
		currentFloorOfLift = lifts[0].getCurrentFloor();
		int availableSpace = lifts[0].getAvailableSpace();
		List<Person> peopleToTransfer = floors[currentFloorOfLift].moveToLift(availableSpace);
		for(Person p: peopleToTransfer) {
			lifts[0].arrive(p);
		}
		lifts[0].peopleEnteringOrLeaving();
	}

	
	/**
	 * Transfers people from the floor to the lift when the lift has reached
	 */
	private void transferToFloorFromLift() {
		currentFloorOfLift = lifts[0].getCurrentFloor();
		List<Person> peopleToTransfer = lifts[0].leave();
		for(Person p: peopleToTransfer) {
			floors[currentFloorOfLift].moveToFloor(p);
		}
	}

	
	/**
	 * Performs all the functionalities that a single tick of building should do
	 */
	public void tick() {
		for (Floor f: floors) {
			f.tick();
		}
		lifts[0].tick("open door");
		transferToFloorFromLift();
		transferToLiftFromFloor();
		lifts[0].tick("close door");
		lifts[0].addRequest(floors[currentFloorOfLift].request());
		lifts[0].tick("move");
	}
	
}
