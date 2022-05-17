/**
 * 
 */
package assignment2;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Data;

@Data
public class Buyer implements Runnable {

	/**
	 * 
	 */
	// VARIABLES
	private static final AtomicInteger numberOfBuyers = new AtomicInteger(0);
	private int buyerID;
	private CarShowroom carShowroom;
	Random random = new Random();
	private boolean running = true;
	// end VARIABLES
	
	
	public Buyer(CarShowroom carShowroom) {
		// TODO Auto-generated constructor stub
		this.carShowroom = carShowroom;
		
		buyerID = numberOfBuyers.incrementAndGet();
		carShowroom.getBuyerCount().incrementAndGet();
		System.out.println("A new buyer, #"+buyerID+" has just appeared");
		
	}
	
	
	
	// METHODS
	@Override
	public void run() {
		
		while (running == true) {
			buyCar();
		}
		
	}
	
	public void buyCar() {
		
		synchronized(carShowroom) { // locks code
		
		if (carShowroom.isEmpty() == true) {
			// Statement below can prints multiple times if included in while loop
			System.out.println("Buyer #"+buyerID+" is out of luck, the showroom is empty");
			
			while (carShowroom.isEmpty() == true) { // WHILE
			
				try {
					carShowroom.wait();				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			} // end WHILE
		}// end IF
		
			Car c = carShowroom.getCarList().get(0);
			carShowroom.takeCar();
			System.out.println("Buyer #"+buyerID+" bought the "+c.toString()+" from the showroom "
					+ "\nThis is sale number " +carShowroom.getSaleCount().get());
			carShowroom.notifyAll();
			
		}
		running = false; // stops function from running by negating the condition
		System.out.println("Buyer #"+buyerID+" has left the showroom. :) ");
	} // end BUYCAR
	
	// end METHODS
	
} // end CLASS
