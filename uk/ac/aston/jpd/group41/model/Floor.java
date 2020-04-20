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

/*
 * Generates a Floor {@code Floor}
 * 
 * @author Nishika 
 * @author Marlon 
 * @version 4.0 
 * @since 1.0 
 */
public class Floor {

	private List<Person> workingList = new ArrayList<>();
	private Queue<Person> waitingList = new LinkedList<>();
	private int floorNum;

	/*
	 * Creates a Floor and it takes a specific amount of floors to generate
	 * @param floorNum takes the number of Floors to create 
	 */
	public Floor(int floorNum) {
		this.floorNum = floorNum;
	}
	
	/*
	 * returns the number of Floors
	 * 
	 * @return an int, representing the quantity of floors
	 */
	public int getFloorNumber() {
		return floorNum;
	}

	/*
	 * Transfers people from the Lift to the Floor
	 * 
	 * @param people receives the Floor list
	 */
	public void moveToFloor(Person p) {
		//p.setCurrentFloor(p.getTargetFloor());
		workingList.add(p);
	}

	/*
	 * It transfers people from the working list to the floor queue
	 * 
	 * @param p receives the person to move
	 */
	public void moveToQueue(Person p) {
		waitingList.add(p);
		if(workingList.size()>0)
		workingList.remove(p);
	}
	
	public List<Person> getWorkingList(){
		return workingList;
	}
	
	public void workingListDisplay(){
		int written = 0;
		boolean activated = false;
		for(Person p : workingList) {
			System.out.print(p.getID());
			written++;
			if(written > 10 && activated == false) {
				System.out.println();
				activated = true;
			}
		}
		System.out.println();
	}
	
	public void waitingListDisplay() {
		int written = 0;
		boolean activated = false;
		for(Person p: waitingList) {
			System.out.print(p.getID());
			written++;
			if(written > 10 && activated == false) {
				System.out.println();
				activated = true;
			}
		}
		System.out.println();
	}
	
	public Queue<Person> getWaitingList(){
		return waitingList;
	}

	/*
	 * It returns a List of people from the queue to the lift
	 * 
	 * @param availableSpace available space on the lift
	 * @return List of people that wants to go in the Lift
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

	/*
	 * Requests the floor, if there are people waiting when the floor has left.
	 */
	public int request() {

		if (waitingList.size() > 0) {
			return floorNum;
		} else {
			return -1;
		}
	}

	/*
	 * Ticks all the people working to see if they want to leave and if they want to leave, they are moved to the queue 
	 * 
	 * @see changeFloorProbablity()
	 */
	public void tick() {
		List<Person> people = new ArrayList<>();
		for (Person p : workingList) {
			boolean floor = false;
			if (p instanceof Client) {
				floor = ((Client) p).tick();
			} else if (p instanceof Developer) {
				floor = ((Developer) p).tick();
			} else if (p instanceof EmployeeNotDeveloper) {
				floor = ((EmployeeNotDeveloper) p).tick();
			} else if (p instanceof MaintenanceCrew) {
				floor = ((MaintenanceCrew) p).tick();
			}
			if (floor == true && p.getTargetFloor() != floorNum) {
				people.add(p);
			}
		}
		for (Person p : people) {
			moveToQueue(p);
		}
	}

	/*
	 * Removes the person from the floor 
	 * 
	 * @param p takes the person who is going to leave
	 */
	public void leave(Person p) {
		if (workingList.contains(p))
			workingList.remove(p);
		else if (waitingList.contains(p))
			waitingList.remove(p);
	}
}
