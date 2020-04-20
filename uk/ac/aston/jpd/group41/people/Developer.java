package uk.ac.aston.jpd.group41.people;

import uk.ac.aston.jpd.group41.model.Simulation;

/**
 * Represents a developer 
 * 
 * Extends the Person class
 * 
 * @author Bal
 * @author Marlon
 * @version 3.0
 * @since 1.0
 */

public class Developer extends Person {

	/**
	 * Creates a Developer with its own ID and space.
	 * 
	 * @param ID is a String representing the ID for the Developer
	 * @param space is an integer representing the space occupied by the developer in the lift
	 * @param simulation is the current simulation of the program
	 */
	public Developer(String ID, int space, Simulation s) {
		super(ID, space, s);
		setTargetFloor((int)Math.floor(simulation.getNumOfFloors()/2) + simulation.getRandom().nextInt(simulation.getNumOfFloors()/2));
	}

	/**
	 * Ticks the developer to see if it wants to change floor, returns true whether
	 * they want to change floor and Overrides from person
	 * 
	 * @see Person#tick()
	 * 
	 * @return boolean representing their decision of changing floor
	 */
	@Override
	public boolean tick() {
		if (super.simulation.getRandom().nextDouble() < super.simulation.getP()) {
			setTargetFloor((int) Math.floor(simulation.getNumOfFloors() / 2)
					+ simulation.getRandom().nextInt(simulation.getNumOfFloors() / 2 + 1));
			return true;
		} else {
			return false;
		}
	}
}
