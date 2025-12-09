/**
 * 
 */
package com.bpi.oop.main.exercise2;

/**
 * 
 */
public class Car extends Vehicle implements Refuelable{
	
	public Car () {
		
	}
	
	
	@Override
	public void refuel() {
		// TODO Auto-generated method stub
		System.out.println("Refueling Car !!!");
	}
	
	public void destroy() {
		System.out.println("Car Explosion !!!");
	}

}
