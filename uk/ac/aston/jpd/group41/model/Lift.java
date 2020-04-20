package uk.ac.aston.jpd.group41.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uk.ac.aston.jpd.group41.people.Person;

/**
 * Represents a lift with a specific capacity {@code Lift}
 * 
 * @author nishika
 * @version 3.1.0
 * @since 1.0.0
 */
public class Lift {

	private List<Integer> targetFloors = new ArrayList<Integer>();
	private List<Person> peopleInLift = new ArrayList<Person>();
	private boolean doorClosed;
	private int currentFloor;
	private final int space;
	private int availableSpace;
	private boolean movingUp;
	private final int maxFloor;
	public boolean hasArrived;
	private Simulation simulation;
	private boolean peopleEntering;
	private boolean peopleLeaving;
	private Lift.LiftMovement movement;

	
	/**
	 * Creates a lift with a specific space and indicating the maximum number of
	 * floors in the building
	 * The lift is on the ground floor when it is created and is empty
	 * 
	 * @param space      space is an integer representing the capacity of the lift
	 * @param maxFloor   is a boolean representing the maximum number of floors in
	 *                   the building
	 * @param simulation is the current simulation of the program
	 */
	public Lift(int space, int maxFloor, Simulation simulation) {
		this.space = space;
		this.maxFloor = maxFloor;
		availableSpace = this.space;
		currentFloor = 0;
		doorClosed = true;
		movingUp = true;
		hasArrived = true;
		peopleEntering = false;
		peopleLeaving = false;
		this.simulation = simulation;
		movement =  new LiftMovement();
	}

	
	/**
	 * Returns the the current floor the lift is at
	 * 
	 * @return an integer representing the current floor the lift is at
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	
	/**
	 * Returns the available space in the lift
	 * 
	 * @return an integer representing the space available in the lift
	 */
	public int getAvailableSpace() {
		return availableSpace;
	}

	
	/**
	 * Person entering the lift if the doors are open and there is space available
	 * 
	 * @param p is a Person representing the person entering the lift
	 */
	public void arrive(Person p) {
		if (availableSpace >= p.getSpace() && !doorClosed && p != null) {
			int targetFloorForPerson = p.getTargetFloor();
			peopleInLift.add(p);
			peopleEntering = true;
			if (!targetFloors.contains(targetFloorForPerson)) {
				targetFloors.add(targetFloorForPerson);
			}
			availableSpace = availableSpace - p.getSpace();
			System.out.println("I have arrived inside the lift " + p.getID() + " " + availableSpace);
		}
	}

	
	/**
	 * People leaving the lift when they have reached their target floors
	 * 
	 * @return a List of Person representing all the people leaving the lift
	 */
	public List<Person> leave() {
		List<Person> peopleLeaving1 = new ArrayList<>();
		for (Person p : peopleInLift) {
			int targetFloorForPerson = p.getTargetFloor();
			if (targetFloorForPerson == currentFloor) {
				peopleLeaving1.add(p);
				p.setCurrentFloor(targetFloorForPerson);
				availableSpace = availableSpace + p.getSpace();
				targetFloors.remove((Integer) targetFloorForPerson);
				peopleLeaving = true;
			}
		}
		for (Person p : peopleLeaving1) {
			peopleInLift.remove(p);
		}
		for (Person p : peopleLeaving1) {
			System.out.println("We have left " + p.getID());
		}
		peopleEnteringOrLeaving();
		return peopleLeaving1;
	}

	
	/**
	 * Ticking the simulation when people are entering or leaving
	 */
	public void peopleEnteringOrLeaving() {
		if (peopleEntering) {
			simulation.addTick();

		} else if (peopleLeaving) {
			simulation.addTick();
		} else if (peopleEntering == true && peopleLeaving == true) {
			simulation.reduceTick();
		}
		System.out.println("Current tick: " + simulation.getTick());
		peopleEntering = false;
		peopleLeaving = false;
	}

	
	/**
	 * The doors of the lift closing
	 */
	public void doorClosed() {
		doorClosed = true;
		hasArrived = false;
		System.out.println("Doors closing");
		simulation.addTick();
		System.out.println("Current tick: " + simulation.getTick());
	}

	
	/**
	 * The doors of the lift opening if the lift has arrived on its target floor
	 */
	public void doorOpened() {
		if (hasArrived)
			doorClosed = false;
		System.out.println("Doors opening");
	}
	

	/**
	 * Does what a tick of elevator should do Lift 
	 * Does different things in different ticks
	 * 
	 * @param task is a String representing the particular task that the lift should
	 *             perform in that tick
	 */
	public void tick(String task) {
		if (task.equals("open door")) {
			doorOpened();
			
		}
		else if (task.equals("close door")) {
			doorClosed();
		}
		else if (task.equals("move")) {
			simulation.addTick();
			movement.move();
		}
	}

	/**
	 * Accepts a request from any floor
	 * 
	 * @param floor is an integer representing the floor the request came from
	 */
	public void addRequest(int floor) {
		if (floor > -1 && floor < maxFloor && !targetFloors.contains(floor))
			targetFloors.add(floor);
	}
	
	
	/**
	 * Member class of Lift 
	 * Deals with the movement of the lift
	 * Determining direction, getting the target floors and moving
	 * @author nishi
	 * @since 5.0.0
	 * @version 1.0
	 *
	 */
	private class LiftMovement{
		
		/**
		 * Moving the lift if there are requests it will go to the respective floors,
		 * Otherwise the lift would go to the ground floor
		 */
		public void move() {
			direction();
			int targetFloor = getNextTargetFloor();
			if (currentFloor < targetFloor) {
				movingUp = true;
				while (doorClosed == true) {
					currentFloor++;
					simulation.addTick();
					System.out.println("Currently in: " + currentFloor + " " + simulation.getTick());
					if (currentFloor == targetFloor) {
						hasArrived = true;
						System.out.println("Lift has arrived to the target floor: " + targetFloor + ", current tick: "
								+ simulation.getTick());
						break;
					}
				}
			} else if (currentFloor > targetFloor) {
				movingUp = false;
				while (doorClosed == true) {
					currentFloor--;
					simulation.addTick();
					System.out.println("Currently in:" + currentFloor + " " + simulation.getTick());
					;
					if (currentFloor == targetFloor) {
						hasArrived = true;
						System.out.println("Lift has arrived to the target floor: " + targetFloor + ", current tick: "
								+ simulation.getTick());
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Determines the direction of the lift
	 */
	public void direction() {
		if (currentFloor == maxFloor) {
			movingUp = false;
		} else if (currentFloor == 0) {
			movingUp = true;
		}
		int pendingFloor = currentFloor;
		if (movingUp) {
			movingUp = false;
			while (pendingFloor != maxFloor) {
				pendingFloor++;
				if (targetFloors.contains(pendingFloor)) {
					movingUp = true;
					break;
				}
			}
		} else if (!movingUp) {
			movingUp = true;
			while (pendingFloor != 0) {
				pendingFloor--;
				if (targetFloors.contains(pendingFloor)) {
					movingUp = false;
					break;
				}
			}
		}
	}
	
	/**
	 * Gets the next target floor for the lift based on whether its moving up or
	 * down
	 * 
	 * @return an integer representing the next target floor
	 */
	public int getNextTargetFloor() {
		int targetFloor = 0;
		List<Integer> floorsUp = new ArrayList<>();
		List<Integer> floorsDown = new ArrayList<>();

		for (int f : targetFloors) {
			if (movingUp) {
				if (f > currentFloor && f <= maxFloor) {
					floorsUp.add(f);
				}
			} else {
				if (f < currentFloor && f > -1) {
					floorsDown.add(f);
				}
			}
		}
		if (movingUp && floorsUp.size() > 0) {
			Collections.sort(floorsUp);
			targetFloor = floorsUp.get(0);
		} else if (!movingUp && floorsDown.size() > 0) {
			Collections.sort(floorsDown);
			targetFloor = floorsDown.get(floorsDown.size() - 1);
		}
		System.out.println("Target floor is: " + targetFloor);
		return targetFloor;
	}
}