package uk.ac.aston.jpd.group41.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//import java.util.PriorityQueue; We decided to use LinkedList 
import java.util.Queue;

import uk.ac.aston.jpd.group41.people.Client;
import uk.ac.aston.jpd.group41.people.Developer;
import uk.ac.aston.jpd.group41.people.EmployeeNotDeveloper;
import uk.ac.aston.jpd.group41.people.MaintenanceCrew;
import uk.ac.aston.jpd.group41.people.Person;

/**
 * Represents a Floor in the Building{@code Floor}
 * 
 * @author Nishika 
 * @author Marlon 
 * @version 4.1.0 
 * @since 1.0 
 */
public class Floor {

	private List<Person> workingList = new ArrayList<>();
	private Queue<Person> waitingList = new LinkedList<>();
	private int floorNum;

	/**
	 * Creates a Floor with the floor number
	 * @param floorNum is an integer representing the floor number of the floor
	 */
	public Floor(int floorNum) {
		this.floorNum = floorNum;
	}
	
	/**
	 * Returns the floor number of the Floor
	 * 
	 * @return an integer representing the floor number
	 */
	public int getFloorNumber() {
		return floorNum;
	}

	/**
	 * Transfers a person to the Floor
	 * Puts them into the working list of the Floor
	 * 
	 * @param p represents the Person to be transferred to the Floor
	 */
	public void moveToFloor(Person p) {
		workingList.add(p);
	}

	/**
	 * Transfers a person from the working list to the floor's waiting queue for the lift
	 * 
	 * @param p represents the Person to be transferred to the waiting queue 
	 */
	public void moveToQueue(Person p) {
		//When a person enters from the ground floor
		if(workingList.size()>0) {
			removePerson(p);
		}
		waitingList.add(p);
	}


	/**
	 * Returns a List of people to be transferred from the queue to the lift
	 * Based on the space available in the lift
	 * 
	 * @param availableSpace is an integer representing available space on the lift
	 * @return List of people to be transferred to the Lift
	 */
	public List<Person> moveToLift(int availableSpace){
		List<Person> people = new ArrayList<Person>();
		for(int i = 0; i< availableSpace && waitingList.size() > 0; i++) {
			if(waitingList.element() instanceof MaintenanceCrew) {
				Person person = waitingList.element();
				waitingList.remove();
				if(availableSpace == 4 && i==0) {
				    people.add(person);
				    break;
				}
				else {
					waitingList.add(person);
				}
			}
			people.add(waitingList.element());
			waitingList.remove();
		}
		return people;
	}

	
	/**
	 * Requests the floor, if there are people waiting when the lift has left.
	 */
	public int request() {
		if (waitingList.size() > 0) {
			return floorNum;
		} else {
			return -1;
		}
	}

	
	/**
	 * Ticks all the people working to check if they want to leave or change floors
	 * If they want to leave or change floors, they are moved to the queue waiting for the Lift
	 * 
	 * @see changeFloorProbablity()
	 */
	public void tick() {
		List<Person> people = new ArrayList<>();
		for (Person p : workingList) {
			boolean changeFloor = false;
			if (p instanceof Client) {
				changeFloor = ((Client) p).tick();
			} else if (p instanceof Developer) {
				changeFloor = ((Developer) p).tick();
			} else if (p instanceof EmployeeNotDeveloper) {
				changeFloor = ((EmployeeNotDeveloper) p).tick();
			} else if (p instanceof MaintenanceCrew) {
				changeFloor = ((MaintenanceCrew) p).tick();
			}
			if (changeFloor == true && p.getTargetFloor() != floorNum) {
				people.add(p);
			}
		}
		for (Person p : people) {
			moveToQueue(p);
		}
	}

	
	/**
	 * Removes the person from the waiting queue or the working list of the floor
	 * 
	 * @param p represents the Person to be removed
	 */
	public void removePerson(Person p) {
		if (workingList.contains(p))
			workingList.remove(p);
		else if (waitingList.contains(p))
			waitingList.remove(p);
	}
}
