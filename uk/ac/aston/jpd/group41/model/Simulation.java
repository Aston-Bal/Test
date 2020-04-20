package uk.ac.aston.jpd.group41.model;

import java.util.Random;

import uk.ac.aston.jpd.group41.people.Person;

/**
 * Holds the all the elements to be simulated
 * 
 * @author Bal
 * @version 1.0
 * @since JDK 11
 */

public class Simulation {
	
	private int tick = 0;
	private Building building;
	private PersonGenerator personGen;
	private Stats stats;
	private double p = 0.001;
	private double q = 0.002;
	private Random rnd;
	private int numOfFloors = 7;

	
	/**
	 * Constructs a simulation element, holds a Random object that will be used to
	 * generate people, this value should be passed with a seed
	 * 
	 * @param rnd a Random object to be used throughout the simulation
	 */
	public Simulation(Random rnd) {
		this.rnd = rnd;
		this.building = new Building(numOfFloors, 1, this);
		this.personGen = new PersonGenerator(this, q,rnd);
		personGen.createDeveloper(10);
		personGen.createEmployeeNotDevelopers(10);
		// this.stats = new Stats(this);
	}

	
	/**
	 * Adds a person to the simulation 
	 * 
	 * @param p represents the Person to be added
	 */
	public void arrive(Person p) {
		building.arrive(p);
		// stats.arrive(c);
	}

	
	/**
	 * Removes a person from the simulation when the person leaves
	 * 
	 * @param p represents the Person who wants to leave
	 */
	public void leave(Person p) {
		building.leave(p); // needs to take out a person not empty array
	}

	
	/**
	 * Ticks the other elements of the simulation that need to be ticked
	 * 
	 */
	public void tick() {
		personGen.tick();
		building.tick();
	}

	
	/**
	 * Adds a tick to the tick of the simulation
	 */
	public void addTick() {
		if(getTick() != 2880) {
			tick++;
		} 
	}
	
	
	/**
	 * Reduces a tick from the simulation tick
	 */
	public void reduceTick() {
		tick -= 1;
	}

	/**
	 * Returns the current tick of the simulation
	 * 
	 * @return an integer value representing the current tick of the simulation
	 */
	public int getTick() {
		return tick;
	}

	
	/**
	 * Returns the current {@code Building} being used in the simulation
	 * 
	 * @return Building
	 */
	public Building getBuilding() {
		return building;
	}

	
	/**
	 * Return the {@code PersonGenerator} of the simulation
	 * 
	 * @return PersonGenerator
	 */
	public PersonGenerator getPersonGen() {
		return personGen;
	}

	
	/**
	 * Sets the probability p, which is the probability of people changing floors
	 * 
	 * @param p  a double value representing the value of p
	 */
	public void setP(double p) {
		this.p = p;
	}

	
	/**
	 * Sets the probability q, which is the probability of the clients entering the building
	 * 
	 * @param q a double value representing the value of q
	 */
	public void setQ(double q) {
		this.q = q;
	}

	
	/**
	 * Returns the probability p being used in the current simulation
	 * 
	 * @return a double value representing the value of p
	 */
	public double getP() {
		return p;
	}

	
	/**
	 * Returns the probability q being used in the current simulation
	 * 
	 * @return a double value representing the value of q
	 */
	public double getQ() {
		return q;
	}

	
	/**
	 * Returns the current {@code Stats} in the simulation
	 * 
	 * @return Stats
	 */
	public Stats returnStats() {
		return stats;
	}

	
	/**
	 * Returns the current Random object being used in the simulation
	 * 
	 * @return a Random object
	 */
	public Random getRandom() {
		return rnd;
	}

	
	/**
	 * Returns the number of floors set in the building
	 * 
	 * @return an integer value representing the number of floors
	 */
	public int getNumOfFloors() {
		return numOfFloors;
	} 
	
	
	/**
	 * Runs the simulation for a given time 
	 * 
	 * @param totalBuildingSimulationTime is an integer representing the amount of time
	 * for which the simulation will run
	 */
	public void run(int totalBuildingSimulationTime) {
		while(tick < totalBuildingSimulationTime) {
			tick();
			System.out.println("Simulation Tick " + tick);
		}
	}
}
