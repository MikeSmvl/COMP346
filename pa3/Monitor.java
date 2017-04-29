/**
 * Class Monitor
 * To synchronize dining philosophers.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 * 
 * Mikael Samvelian 40003178
 * COMP 346 - Assignment 3
 */
public class Monitor
{
	/*
	 * ------------
	 * Data members
	 * ------------
	 */

	//Array that holds the chopsticks around the table
	Chopstick[] chopsticks;
	//Integer that holds the number of philosophers
	int numberOfPhilosophers;
	//Boolean that remembers whether someone is talking or not
	boolean someonesTalking;
		
	/**
	 * Class for chopstick
	 * Stores whether it is picked up and the
	 * last philosopher who picked it up
	 */
	private class Chopstick
	{
		public boolean pickedUp;
		public int lastPickedUpBy;
		
		public Chopstick()
		{
			pickedUp = false;
			lastPickedUpBy = 0;
		}
		
		/**
		 * Determines whether a chopstick was last picked up by a philosopher
		 * @param piTID Thread ID of the philosopher
		 * @return the philosopher that last picked the chopstick
		 */
		public boolean lastPickedUpByMe(final int piTID)
		{
			return lastPickedUpBy == piTID;
		}
		
		/**
		 * Determines whether a chopstick is currently picked up by another philosopher 
		 * @param piTID Thread ID of the philosopher
		 * @return True if the chopstick is picked up and the philosopher ID does not match
		 */
		public boolean pickedUpByAnother(final int piTID)
		{
			return lastPickedUpBy != piTID && pickedUp;
		}
		
		/**
		 * Pick up a chopstick and set the new philosopher
		 * @param piTID  Thread ID of the philosopher
		 */
		public void pickUp(final int piTID)
		{
			pickedUp = true;
			lastPickedUpBy = piTID;
		}
		
		/**
		 * Put a chopstick down
		 */
		public void putDown()
		{
			pickedUp = false;
		}
	}
	
	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		numberOfPhilosophers = piNumberOfPhilosophers;
		chopsticks = new Chopstick[piNumberOfPhilosophers];
		
		//initialize the chopsticks
		for(int i = 0; i < chopsticks.length; i++)
		{
			chopsticks[i] = new Chopstick();
		}
		
	}

	/*
	 * -------------------------------
	 * User-defined monitor procedures
	 * -------------------------------
	 */

	/**
	 * Grants request (returns) to eat when both chopsticks/forks are available.
	 * Else forces the philosopher to wait()
	 */
	public synchronized void pickUp(final int piTID)
	{
		//chopstick id of a corresponding philosopher Thread ID
		int pid = piTID-1;
		
		while(true)
		{
			if(chopsticks[pid].pickedUpByAnother(piTID) || chopsticks[(pid + 1) % numberOfPhilosophers].pickedUpByAnother(piTID)) 
			{
				//at least one chopstick is picked up by someone 
				//prioritize hungry philosophers by allowing philosophers who were not
				//the last users of available chopsticks to pick them up while waiting
				//for the other chopstick to become available.
				if(!chopsticks[pid].pickedUp && !chopsticks[pid].lastPickedUpByMe(piTID))
				{
					//chopstick on the left
					chopsticks[pid].pickUp(piTID);
				} 
				else if(!chopsticks[(pid + 1) % numberOfPhilosophers].pickedUp && !chopsticks[(pid + 1) % numberOfPhilosophers].lastPickedUpByMe(piTID))
				{
					//chopstick on the right
					chopsticks[(pid + 1) % numberOfPhilosophers].pickUp(piTID);
				}
			} 
			else
			{
				//if both chopsticks are available, pick them up (even though you may already have them) and leave the loop
				chopsticks[pid].pickUp(piTID);
				chopsticks[(pid + 1) % numberOfPhilosophers].pickUp(piTID);
				break;
			}
			
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println("Monitor.pickUp():");
				DiningPhilosophers.reportException(e);
				System.exit(1);
			}
		}
	}

	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{
		//put down chopsticks
		chopsticks[piTID - 1].putDown();
		chopsticks[(piTID) % numberOfPhilosophers].putDown();
		
		notifyAll();
	}

	/**
	 * Only one philopher at a time is allowed to philosophy
	 * (while she is not eating).
	 */
	public synchronized void requestTalk()
	{
		//wait while someone is talking
		while(someonesTalking) 
		{
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println("Monitor.requestTalk():");
				DiningPhilosophers.reportException(e);
				System.exit(1);
			}
		}
		
		//whoever requested is now talking
		someonesTalking = true;
	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk()
	{
		//whoever requested is no longer talking
		someonesTalking = false;
		notifyAll();
	}
}

// EOF