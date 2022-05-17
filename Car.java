/**
 * 
 */
package assignment2;

import java.util.Random;
import lombok.Data;

@Data
public class Car { // CLASS

	/**
	 * 
	 */
	// VARIABLES
	private String registration;
	private int saleValue;
	private String colour;	
	Random random = new Random();
	static final String [] colours = {"red", "blue", "yellow", "green", "black", "silver", "white", "hot pink"}; 
	static final String [] counties = {"G", "CE", "TS", "TN", "L", "LK", "MH", "D", "LM", "MO", "SO", "C", "WD", "KK", "CW", "WX", "MN", "CN", "DL", "WW", "KE", "LS", "OY", "RN", "WM", "KY", "LD"};  
	
	// end VARIABLES
	
	
	public Car() { // CONSTRUCTOR
		// TODO Auto-generated constructor stub
		registration = genRegistration();
		saleValue = genSaleValue();
		colour = genColour();
	} // end CONSTRUCTOR

	
	// METHODS
	public String genColour() {
		colour = colours[random.nextInt(colours.length)];
		return colour;
	}

	public String genRegistration() {
		// To set String in format 00-G-0000 
		String year = String.format("%02d", random.nextInt(23)); // generates number between 00 and 22 as String
		String county = counties[random.nextInt(counties.length)]; // use array length, to avoid miscalculations and avoid multiple changes if array or code amended
		String code = String.format("%04d", random.nextInt(10000));
		registration = year + "-" + county + "-" +code;
		return registration;
	}
	

	public Integer genSaleValue() {
		/* Use of recursive genValue() to ensure "randomness"
		and parameters met. */
		Integer min = 1000; 
		Integer max = 20000;
		Integer rand = random.nextInt(20001);
		// IF LOOPS to ensure randomness
			if (rand < min) { // check if rand < min 
				rand += genSaleValue(); // if add another rand number
			}
				if (rand > max) { // if rand now > max, take rand number
					rand -= genSaleValue();
				} 
		// end IF LOOPS
			return rand; // once rand > min && < max, return
		}
	
	
	@Override
	public String toString() {
		return colour+ " car with registration " +registration+ " worth €" +saleValue;
	
	} // end METHODS
	
} // end CLASS
