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
	private boolean hasReached;

	/**
	 * Creates a person with a unique ID and space that it occupies
	 * 
	 * @param space       is the space the person occupies in the lift
	 * @param ID          is the unique ID of the person
	 * @param s 		Simulation of the main program
	 */
	public Person(String ID, int space, Simulation s) {
		this.space = space;
		this.ID = ID;
		this.simulation = s;
	}

	/**
	 * Returns the person's ID
	 * 
	 * @return A string representing the ID
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Returns the space the person occupies in the lift
	 * 
	 * @return An integer representing the space
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
	 * @return An integer representing the target floor
	 */
	public int getTargetFloor() {
		return targetFloor;
	}

	/**
	 * Sets the next target floor for the person
	 * 
	 * @param targetFloor contains the next target floor
	 */
	protected void setTargetFloor(int targetFloor) {
		this.targetFloor = targetFloor;
	}

	/**
	 * Does what a single tick should do for a person based on the type of person
	 * @return boolean return if they are ticking or not
	 */
	public abstract boolean tick();

	/*
	 * returns current floor
	 * 
	 * @return integer representing the value of current floor
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	/*
	 * sets the current floor number
	 * 
	 * @param currentFloor takes an integer as the new currentFloor value
	 */
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	/*
	 * returns hasReached value
	 * 
	 * @return boolean representing the value of hasReached
	 */
	public boolean isHasReached() {
		return hasReached;
	}

	/*
	 * sets the value has Reached
	 * 
	 * @param hasReached is the new value of hasReached
	 */
	public void setHasReached(boolean hasReached) {
		this.hasReached = hasReached;
	}

}