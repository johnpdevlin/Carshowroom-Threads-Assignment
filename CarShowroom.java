package assignment2;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Data;
@Data
public class CarShowroom {
	
	// VARIABLES
	private static final int capacity = 9;
	private ArrayList<Car> carList = new ArrayList<Car>();
	private AtomicInteger saleCount = new AtomicInteger(0);
	private AtomicInteger buyerCount = new AtomicInteger();
	private AtomicInteger sellerCount = new AtomicInteger(); 
	// end VARIABLES
	
	public CarShowroom() { 
		isFull();
		isEmpty();
    }
	
	
	// BOOLEANS
	public synchronized boolean isFull() {
	    if(carList.size() == capacity) {
	        return true;
	    }
	    return false;
	}
    
	
	public synchronized boolean isEmpty() {
	    if(this.carList.isEmpty()) {
	        return true;
	    }
	    return false;
	}
    

	// end BOOLEANS

	// METHODS
	public void addCar(Car c) {
		carList.add(c);
	}
	
	public void takeCar() {
		carList.remove(0);
		buyerCount.getAndDecrement();
		saleCount.getAndIncrement();
	}
	
	public synchronized AtomicInteger getBuyerCount() {
		return buyerCount;
	}
	
	public synchronized AtomicInteger getSellerCount() {
		return sellerCount;
	}
	
	public synchronized ArrayList<Car> getCarList(){
		return carList;
	}
	
// end METHODS

} // end CLASS
