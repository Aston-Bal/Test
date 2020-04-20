package uk.ac.aston.jpd.group41.model;

import java.util.Random;

import uk.ac.aston.jpd.group41.people.Person;
import uk.ac.aston.jpd.group41.textview.TextView;

/**
 * This {@code Simulation} class is the class that holds the all the elements to
 * be simulated
 * 
 * @author Bal
 * @version 1.0
 * @since JDK 11
 */

public class Simulation {
	private static int tick = 0;
	private Building building;
	private PersonGenerator personGen;
	private Stats stats;
	private double p = 0.005;
	private double q = 0.01;
	private Random rnd;
	private int numOfFloors = 7;
	private boolean ticking = false;
	private int tickView = 0;

	/**
	 * Constructs a simulation element, holds a Random object that will be used to
	 * generate people, this value should be passed with a seed
	 * 
	 * @param rnd A Random object
	 */

	public Simulation(Random rnd) {
		this.rnd = rnd;
		this.building = new Building(numOfFloors, 1, this);
		this.personGen = new PersonGenerator(this, q);
		personGen.createDeveloper(10);
		personGen.createEmployeeNotDevelopers(10);
		// this.stats = new Stats(this);
	}

	/**
	 * Arrive adds a person into the simulation
	 * 
	 * @param p Instance of Person
	 */
	public void arrive(Person p) {
		building.arrive(p);
		// stats.arrive(c);
	}

	/**
	 * Exits a person from the simulation
	 * 
	 * @param p Instance of Person
	 */
	public void leave(Person p) {
		building.leave(p); // needs to take out a person not empty array
	}

	/**
	 * Tick is the tick of the simulation ticking other elements that need to be
	 * ticked
	 */
	public void tick() {
		personGen.tick();
		building.tick();
		this.getBuilding().getLifts();
	}

	public void addTick() {
		if(getTick() != 2880) {
			tick++;
			ticking= true;
		} 
	}
	
	public void addTickView() {
		if(ticking) {
			tickView++;
		}
		ticking = false;
	}
	
	public int getTickView() {
		return tickView;
	}
	
	public void reduceTick() {
		tick -= 1;
	}

	/**
	 * Returns the current tick of the simulation
	 * 
	 * @return Int value
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
	 * Sets the probability P for the current simulation run
	 * 
	 * @param p Double value
	 */
	public void setP(double p) {
		this.p = p;
	}

	/**
	 * Sets the probability Q for the current simulation run
	 * 
	 * @param q Double value
	 */
	public void setQ(double q) {
		this.q = q;
	}

	/**
	 * Returns the current probability being used for P
	 * 
	 * @return Double value
	 */
	public double getP() {
		return p;
	}

	/**
	 * Returns the current probability being used for Q
	 * 
	 * @return Double value
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
	 * @return Random
	 */
	public Random getRandom() {
		return rnd;
	}

	/**
	 * Returns the amount of floors set in the building
	 * 
	 * @return Int value
	 */
	public int getNumOfFloors() {
		return numOfFloors;
	} // This shouldn't be here

	/**
	 * Number of floors to be set in the building
	 * 
	 * @param numOfFloors Int value
	 */
	public void setNumOfFloors(int numOfFloors) {
		this.numOfFloors = numOfFloors;
	} 
	
	/*
	 * Runs the simulation for a given time 
	 * 
	 * @param totalBuildingSimulationTime this is the time for how long the simulation runs
	 */
	 public void run(int totalBuildingSimulationTime) {
		TextView view = new TextView();
		/*while(getTick() < totalBuildingSimulationTime) {
			//tick();
		}*/
		for (int i = 0; i < totalBuildingSimulationTime; i++) {
			tick();
			addTickView();
			view.display(this);
		}
		
		/*for(int i = 0; i < totalBuildingSimulationTime; i++) {
			System.out.println("Simulation Tick " + getTick());
			view.display(this);
		}*/
	}
}
