package uk.ac.aston.jpd.group41.people;

import uk.ac.aston.jpd.group41.model.Simulation;

/*
 * Creates a developer 
 * 
 * @author Bal
 * @author Marlon
 * @version 3.0
 * @since 1.0
 */

public class EmployeeNotDeveloper extends Person {

	/*
	 * Creates an Employee Not Developer with its own ID and space.
	 * 
	 * @param ID sets the ID for the Employee Not Developer
	 * 
	 * @param space sets how much it occupies
	 * 
	 * @param simulation is the current simulation of the program
	 */
	public EmployeeNotDeveloper(String ID, int space, Simulation s) {
		super(ID, space, s);
		setTargetFloor(1+(simulation.getRandom().nextInt(simulation.getNumOfFloors()-1)));
	}

	/*
	 * Ticks the employee to see if it wants to change floor, returns true whether
	 * they want to change floor and overrides from person
	 * 
	 * @see Person#tick()
	 * 
	 * @return boolean representing their decision of changing floor
	 */
	@Override
	public boolean tick() {
		if (super.simulation.getRandom().nextDouble() < super.simulation.getP()) {
			setTargetFloor(1+(super.simulation.getRandom().nextInt((super.simulation.getNumOfFloors() - 1))));
			return true;
		} else {
			return false;
		}
	}
}
