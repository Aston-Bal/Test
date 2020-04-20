package uk.ac.aston.jpd.group41.people;

import uk.ac.aston.jpd.group41.model.Simulation;

/**
 * Represents a Maintenance Crew 
 * 
 * Extends the Person class 
 * 
 * @author Bal
 * @author Marlon
 * @version 3.0
 * @since 1.0
 */

public class MaintenanceCrew extends Person {

	
	/**
	 * Creates a Maintenance Crew and sets the desired Floor.
	 * 
	 * @param ID is String representing the ID of the Maintenance Crew
	 * @param space is an integer representing the space occupied by the Maintenance Crew in the lift
	 * @param simulation represents the main Simulation of the program
	 */
	public MaintenanceCrew(String ID, int space, Simulation simulation) {
		super(ID, space, simulation);
		setTargetFloor(simulation.getNumOfFloors()-1);
	}

	
	/**
	 * After reaching the target floor, generates a random time to stay in the building
	 * After the time is over, they leave the building
	 * 
	 * @see Person#tick()
	 * 
	 * @return a boolean value depending on whether they are leaving or not
	 */
	public boolean tick() {
		int tickStarted = super.simulation.getTick();
		int low = 20;
		int high = 40;
		int timeInBuilding = super.simulation.getRandom().nextInt(high - low) + low;
		if (getCurrentFloor() == getTargetFloor()) {
			if (tickStarted >= tickStarted + timeInBuilding) {
				setTargetFloor(0);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}