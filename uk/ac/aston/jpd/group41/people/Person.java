package uk.ac.aston.jpd.group41.people;

import uk.ac.aston.jpd.group41.model.Simulation;


/**
 * Represents a Person in the building.
 * 
 * @author Nishika
 * @version 3.0
 * @since JDK 11
 */

public abstract class Person {

	private final int space;
	private final String ID;
	private int targetFloor;
	private int currentFloor;
	protected Simulation simulation;

	
	/**
	 * Creates a person with a unique ID and space that it occupies
	 * 
	 * @param space is an integer representing the space the person occupies in the lift
	 * @param ID is a String representing the unique ID of the person
	 * @param s is the Simulation of the main program
	 */
	public Person(String ID, int space, Simulation s) {
		this.space = space;
		this.ID = ID;
		this.simulation = s;
	}

	
	/**
	 * Returns the person's ID
	 * 
	 * @return a String value representing the ID
	 */
	public String getID() {
		return ID;
	}

	
	/**
	 * Returns the space the person occupies in the lift
	 * 
	 * @return an integer value representing the space the person occupies in the lift
	 */
	public int getSpace() {
		return space;
	}

	
	/**
	 * Returns the simulation of the program
	 * 
	 * @return Simulation representing the simulation of the program
	 */
	public Simulation getSimulation() {
		return simulation;
	}

	
	/**
	 * Returns the next target floor for the person
	 * 
	 * @return An integer representing the next target floor
	 */
	public int getTargetFloor() {
		return targetFloor;
	}

	
	/**
	 * Sets the next target floor for the person
	 * 
	 * @param targetFloor  is an integer containing the next target floor
	 */
	protected void setTargetFloor(int targetFloor) {
		this.targetFloor = targetFloor;
	}

	
	/**
	 * Does what a single tick should do for a person based on the type of person
	 * 
	 * @return a boolean value indicating a change in their behaviour
	 */
	public abstract boolean tick();

	
	/**
	 * Returns current floor the person is in
	 * 
	 * @return an integer value representing the current floor of the person
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	
	/**
	 * Sets the current floor number the person is in
	 * 
	 * @param currentFloor is an integer representing the current floor the person is in
	 */
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

}