/**
 * 
 */
package assignment2;

import java.util.Random;

/**
 * @author j
 *
 */

public class Main { // CLASS
	
	
	public static void main(String[] args) throws InterruptedException { // MAIN
		// TODO Auto-generated method stub

	Random random = new Random();
	CarShowroom carShowroom = new CarShowroom();
	int day = 1;
		
			while (day <= 30) { // WHILE
				
				System.out.println("\n*** Day "+day+" is beginning. There are " +carShowroom.getCarList().size()+ " cars in the showroom as the doors are opened ***");
				
				Integer buyers = random.nextInt(2); Integer sellers = random.nextInt(2); // random creates random amount of buyers and sellers
				
				Integer p1 = 10;
				for (int i = 0; i <= buyers; i++) { // FOR
					
					Buyer b = new Buyer(carShowroom);
					Thread buyThread = new Thread(b);
					buyThread.setPriority(p1); // gives priority to oldest recent threads
					buyThread.start();	
					p1 = p1 - 3; // reduces priority for older threads
					Thread.sleep(200); // slows program
				} // end FOR
				
			
				Integer p2 = 8;
				for (int i = 0; i <= sellers; i++) { // FOR
					
					Seller s = new Seller(carShowroom);
					Thread sellThread = new Thread(s);
					sellThread.start();	
					sellThread.setPriority(p2); // gives priority to older threads
			        p2 = p2 - 2; // reduces priority for older threads
			        Thread.sleep(200); // slows program
				}// end FOR	
				day++;
				
			} // end WHILE
	
			System.out.println("\nThe month is over. There are " + carShowroom.getCarList().size()+ " cars left in the showroom. There have been "+carShowroom.getSaleCount()+" sales in total.");
			
	} // end MAIN

} // end CLASS
