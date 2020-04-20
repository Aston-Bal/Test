package uk.ac.aston.jpd.group41.people;

import uk.ac.aston.jpd.group41.model.Simulation;

/*
 * Generates a MaintenanceCrew 
 * 
 * @author Bal
 * @author Marlon
 * @version 3.0
 * @since 1.0
 */

public class MaintenanceCrew extends Person {

	/*
	 * It creates a Maintenance Crew and sets the desired Floor.
	 * 
	 * @param ID sets the ID
	 * 
	 * @param space sets how much they occupy
	 * 
	 * @param simulation is the current time simulation
	 **/
	public MaintenanceCrew(String ID, int space, Simulation simulation) {
		super(ID, space, simulation);
		setTargetFloor(simulation.getNumOfFloors()-1);
	}

	/*
	 * Once they have entered the building, it generates a random meeting time for
	 * the maintenance crew and when it finishes, they leave.
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