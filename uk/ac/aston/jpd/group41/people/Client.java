package uk.ac.aston.jpd.group41.people;

import uk.ac.aston.jpd.group41.model.Simulation;

/**
 * Represents  a Client
 * 
 * Extends the Person class
 * 
 * @author Bal
 * @author Marlon
 * @version 3.0
 * @since 1.0
 */

public class Client extends Person {

	private boolean isPatient;

	
	/**
	 * Creates a Client with its own ID and space.
	 * 
	 * @param ID is a String representing the ID of the Client
	 * @param space is an integer representing the space occupied by the Client in the lift
	 * @param simulation is the current Simulation of the program
	 */
	public Client(String ID, int space, Simulation simulation) {
		super(ID, space, simulation);
		isPatient = simulation.getRandom().nextBoolean();
		setTargetFloor(simulation.getRandom().nextInt((simulation.getNumOfFloors())/2));
	}

	
	/**
	 * Returns whether the client is patient or not
	 * Returns true if they are patient and false otherwise
	 * 
	 * @return a boolean representing if they are patient
	 */
	public boolean isPatient() {
		return isPatient;
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
		int low = 10;
		int high = 30;
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
