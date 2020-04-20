package uk.ac.aston.jpd.group41.textview;

import uk.ac.aston.jpd.group41.model.Floor;
import uk.ac.aston.jpd.group41.model.Simulation;

public class TextView {

	public void display(Simulation simulation) {

		System.out.println();
		System.out.println("--- Tick: " + simulation.getTickView());
		System.out.println("Complaints: ");// add number of complaints
		System.out.println("Average waiting time: ");// add average waiting time
		System.out.println("Elevator used: "); // i
		System.out.println();

		simulation.getBuilding().getLiftCurrentFloor();

		Floor[] floors = simulation.getBuilding().getFloors();
		for (int i = 0; i < simulation.getNumOfFloors(); i++) {
			System.out.println("Floor: " + i);
			System.out.print("People working: ");
			if (floors[i].getWorkingList().isEmpty()) {
				System.out.println("Nobody");
			} else {
				floors[i].workingListDisplay();
			}

			System.out.print("People waiting for the Lift: ");
			if (floors[i].getWaitingList().isEmpty()) {
				System.out.println("Empty");
			} else {
				floors[i].waitingListDisplay();
			}
		}
	}
}