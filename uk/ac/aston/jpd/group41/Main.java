package uk.ac.aston.jpd.group41;

import java.util.Random;
import uk.ac.aston.jpd.group41.model.Simulation;
import uk.ac.aston.jpd.group41.textview.TextView;

/**
 * Runs the entire model {@code Main}
 * 
 * @author Bal
 * @version 1.0
 * @since JDK 11
 */

public class Main {

	/*
	 * Runs the simulation
	 * 
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		Random rnd = new Random(0);
		Simulation s = new Simulation(rnd);
		s.run(2880);

	}

	/*
	 * Runs the simulation for a given time
	 * 
	 * @param totalBuildingSimulationTime this is the time for how long the
	 * simulation runs
	 *
	 * public void run(int totalBuildingSimulationTime) { TextView view = new
	 * TextView(); while(getTick() < totalBuildingSimulationTime) {
	 * System.out.println("Simulation Tick " + getTick()); view.display(this);
	 * tick(); }
	 */
}
