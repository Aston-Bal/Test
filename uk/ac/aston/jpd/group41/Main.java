package uk.ac.aston.jpd.group41;

import java.util.Random;
import uk.ac.aston.jpd.group41.model.Simulation;

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
		s.run(100);
	}
}
