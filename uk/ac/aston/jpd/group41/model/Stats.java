package uk.ac.aston.jpd.group41.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import uk.ac.aston.jpd.group41.people.Person;
import uk.ac.aston.jpd.post.model.CustomerWait;

/*
 * Represents the Stats simulation {@code Stats}
 * 
 * @author JUST GET METHODS DID THE GUY 
 * @version 1.1
 * @since 1.0
 */
public class Stats {

	private double floorProbability_P;
	private double arivalProbability_Q;
	private Random random;
	private double averageWaitingTime = 0;
	private double totalComplaints = 0;
	private Simulation simulation;
	private Map<Person,PersonWait> waitTime = new HashMap<Person,PersonWait>();
	/*
	 * Creates a Stats object
	 * 
	 * @param Simulation takes simulation objects as it holds all the stat's it will need it will be taking  
	 */
	public Stats(Simulation simulation) {
		this.simulation = simulation;
		this.floorProbability_P = simulation.getP();
		this.arivalProbability_Q = simulation.getQ();
		this.random = simulation.getRandom();
	}

	public void arrive(Person p) {
	waitTime.put(p,new PersonWait(p,simulation.getTick()));
	}
	
	public double getAverageWaitingTime() {
		int nSamples = 0;
		double total = 0;

		for (PersonWait pw : waitTime.values()) {
			if (pw.getServingStartedTick() != null) {
				nSamples++;
				total += pw.getServingStartedTick() - pw.getArrivalTick();
			}
		}

		if (nSamples > 0) {
			return total / nSamples;
		} else {
			return Double.NaN;
		}
	}
	
	private class PersonWait {
		private final Person person;
		private final int ArrivalTick;
		private Integer inLift;
		
		public PersonWait(Person person,int ArrivalTick) {
			this.person = person;
			this.ArrivalTick = ArrivalTick;
		}
		public Person getPerson() {return person;}
		public Integer getArrivalTick() {return ArrivalTick;}
		public void setInLift(int inLift) {this.inLift = inLift;}
		public int getInLift() {return inLift;}
	}

}

