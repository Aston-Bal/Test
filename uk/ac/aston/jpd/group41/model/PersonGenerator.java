package uk.ac.aston.jpd.group41.model;

import java.util.Random;
import uk.ac.aston.jpd.group41.people.Client;
import uk.ac.aston.jpd.group41.people.Developer;
import uk.ac.aston.jpd.group41.people.EmployeeNotDeveloper;
import uk.ac.aston.jpd.group41.people.MaintenanceCrew;
import uk.ac.aston.jpd.group41.people.Person;

/*
 * Generates People {@code PersonGenerator}
 * 
 * @author Marlon
 * @version 2.0
 * @since 1.0
 */

public class PersonGenerator {
	private final double clientsProbabiblity_Q;
	private final double maintenanceCrewProbability = 0.005;
	private final Simulation simulation;
	private static int numOfCrew;
	private static int numOfClient;
	
	/*
	 * Creates the PersonGenerator object.
	 * 
	 * @param simulation is the current simulation of the program
	 * 
	 * @param random is used to create random Integers for the arrival Probability
	 * 
	 * @param clientsProbabiblity_Q takes the probability limit.
	 */
	public PersonGenerator(Simulation simulation, double clientsProbabiblity_Q) {
		this.simulation = simulation;
		this.clientsProbabiblity_Q = clientsProbabiblity_Q;
		numOfCrew = 0;
		numOfClient = 0;
	}

	/*
	 * Generates a client
	 */
	public void createClient() {
		numOfClient++;
		Person client = new Client("C["+numOfClient + "] ",1,simulation);
		simulation.arrive(client);
		System.out.println("Client created "+numOfClient);
	}

	/*
	 * Generates a Maintenance Crew
	 */
	public void createMaintenanceCrew() {
		numOfCrew++;
		Person maintenanceCrew = new MaintenanceCrew("MC["+numOfCrew + "] ",4,simulation);
		simulation.arrive(maintenanceCrew);
		System.out.println("MC created "+numOfCrew);
	}

	/*
	 * Generates Employees - Developers.
	 * 
	 * @param quantity specifies how many developers to create
	 */
	public void createDeveloper(int quantity) {
		for (int i = 0; i < quantity; i++) {
			Developer developer = new Developer("D[" + i + "] ", 1, simulation);
			simulation.arrive(developer);
			//System.out.println("Developer created " + i);
		}
	}

	/*
	 * Generates Employees - Not Developers
	 * 
	 * @param quantity specifies how many developers to create
	 */
	public void createEmployeeNotDevelopers(int quantity) {
		for (int i = 0; i < quantity; i++) {
			EmployeeNotDeveloper notDeveloper = new EmployeeNotDeveloper("ND[" + i + "] ", 1, simulation);
			simulation.arrive(notDeveloper);
			//System.out.println("Not Developer created " + i);
		}
	}

	/*
	 * It invokes {@link arrivalProbability()}
	 * 
	 * @see arrivalProbability()
	 */
	public void tick() {
		arrivalProbability();
	}

	/*
	 * Generates clients and maintenance crew based on probability Q.
	 */
	public void arrivalProbability() {
		
		/*trying to do it max 0.005
		double min = 0;
		double max = 0.005;
		double result = Math.random() * (max - min + 1) + min;
		double randomValue = result;
		*/
		double randomValue = simulation.getRandom().nextDouble(); 
		
		if (randomValue < clientsProbabiblity_Q) {
			createClient();
		}
		if (randomValue < maintenanceCrewProbability) {
			createMaintenanceCrew();
		}
	}
}
