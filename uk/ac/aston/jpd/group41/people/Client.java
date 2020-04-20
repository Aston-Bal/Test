package uk.ac.aston.jpd.group41.people;

import uk.ac.aston.jpd.group41.model.Simulation;

/*
 * Generates a Client
 * 
 * @author Bal
 * @author Marlon
 * @version 3.0
 * @since 1.0
 */

public class Client extends Person {

	private boolean isPatient;

	/*
	 * Creates a Client with its own ID and space.
	 * 
	 * @param ID sets the ID for the client
	 * 
	 * @param space sets how much they occupy
	 * 
	 * @param simulation is the current simulation of the program
	 */
	public Client(String ID, int space, Simulation simulation) {
		super(ID, space, simulation);
		isPatient = simulation.getRandom().nextBoolean();
		setTargetFloor(simulation.getRandom().nextInt((simulation.getNumOfFloors())/2));
	}

	/*
	 * returns true if it is patient and false otherwise
	 * 
	 * @return a boolean representing if they are patient
	 */
	public boolean isPatient() {
		return isPatient;
	}

	/*
	 * Once it has entered the building, it generates a random meeting time for the
	 * client and when it finishes, they leave.
	 * 
	 * @return boolean depending whether they are leaving
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
