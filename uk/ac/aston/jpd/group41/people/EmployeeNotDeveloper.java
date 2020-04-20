package uk.ac.aston.jpd.group41.people;

import uk.ac.aston.jpd.group41.model.Simulation;


/**
 * Represents an Employee who is not a Developer
 * 
 * Extends the Person class
 * 
 * @author Bal
 * @author Marlon
 * @version 3.0
 * @since 1.0
 */

public class EmployeeNotDeveloper extends Person {

	/**
	 * Creates an Employee Not Developer with its own ID and space.
	 * 
	 * @param ID is a String representing the ID of the Employee Not Developer
	 * @param space is an integer representing the space occupied by the employee in the lift
	 * @param simulation represents the current Simulation of the program
	 */
	public EmployeeNotDeveloper(String ID, int space, Simulation s) {
		super(ID, space, s);
		setTargetFloor(simulation.getRandom().nextInt(simulation.getNumOfFloors()-1));
	}

	/**
	 * Ticks the employee not developer to see if it wants to change floor
	 * Returns true when they want to change their floor
	 * 
	 * @see Person#tick()
	 * 
	 * @return a boolean value representing their decision of changing floor
	 */
	@Override
	public boolean tick() {
		if (super.simulation.getRandom().nextDouble() < super.simulation.getP()) {
			setTargetFloor(super.simulation.getRandom().nextInt((super.simulation.getNumOfFloors() - 1)));
			return true;
		} else {
			return false;
		}
	}
}
