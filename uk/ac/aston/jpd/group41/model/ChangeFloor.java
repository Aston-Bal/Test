package uk.ac.aston.jpd.group41.model;

import java.util.Random;

import uk.ac.aston.jpd.group41.people.Client;
import uk.ac.aston.jpd.group41.people.Developer;
import uk.ac.aston.jpd.group41.people.EmployeeNotDeveloper;
import uk.ac.aston.jpd.group41.people.MaintenanceCrew;
import uk.ac.aston.jpd.group41.people.Person;

//not using this class, therefore not good javadocs XD
public class ChangeFloor {

	private static final double default_p = 0.002;
	Random random;
	private double p = default_p;
	private Person person;
	private int floorTo;

	public ChangeFloor(Random rnd, Person per, int floorTo) {
		this.random = rnd;
		this.person = per;
		this.floorTo = floorTo;
	}

	public void setP(double p) {
		this.p = p;
	}

	public int generateNewFloor() {
		if (person instanceof Client) {
			return random.nextInt(floorTo + 1);
		} else if (person instanceof EmployeeNotDeveloper | person instanceof Developer) {
			if (random.nextDouble() < p) {
				if (person instanceof EmployeeNotDeveloper) {
					return random.nextInt(floorTo + 1);
				} else {
					return (int) Math.floor(floorTo / 2) + random.nextInt(floorTo / 2 + 1);
				}
			}
		} else if (person instanceof MaintenanceCrew) {
			return floorTo;
		}
		return -1;
	}
}
