/**
 * 
 */
package assignment2;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;
@Data
public class Seller implements Runnable {

	// VARIABLES
	private static final AtomicInteger numberOfSellers = new AtomicInteger(0);
	private int sellerID; 
	Random random = new Random();
	private CarShowroom carShowroom;
	private boolean running = true;
	// VARIABLES
	
	public Seller(CarShowroom carShowroom) {
		// TODO Auto-generated constructor stub
		this.carShowroom = carShowroom;
		sellerID = numberOfSellers.incrementAndGet();
		carShowroom.getSellerCount().incrementAndGet();
		System.out.println("A new seller, #"+sellerID+" has just appeared");
	}

	// METHODS

	
	@Override
	public  void run() {	
		while (running == true) {
			sellCar();
		}
	}
		
		
	
	
	public void sellCar() {
		
		synchronized(carShowroom) {
			Car c = new Car();
			
			
			System.out.println("Seller #"+sellerID+" has a "+c.toString()+" to sell");
			
			if (carShowroom.isFull()) {
				// stops statement printing multiple times
				// though this only occurs when the showroom is full, which is unlikely
				System.out.println("But, Seller #"+sellerID+" is out of luck, the showroom is full");
				while (carShowroom.isFull()) {
					try {
						carShowroom.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
				} // end WHILE
			} // end IF	
				
				carShowroom.addCar(c); // adds car if space in showroom
				System.out.println("Seller #"+sellerID+" sold their " +c.toString()+ " to the showroom ");
				carShowroom.notifyAll(); // notifies now that there is a car in the showroom
				
			}
			running = false; // stops run function from running again
			System.out.println("Seller #"+sellerID+" has left. :O");
	}
	
	// end METHODS
	
} // end CLASS
