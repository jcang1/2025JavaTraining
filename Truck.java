/**
 * 
 */
package com.bpi.oop.main.exercise2;

/**
 * 
 */
public class Truck extends Vehicle implements Refuelable {
	
	private String brand;
	private int wheelCount;
	private int maxCap;
	private int maxLiters;	
	
	public Truck () {
		
	}
	
	public Truck(String brand, int wheelCount, int maxCap, int maxLiters) {
		this.brand = brand;
		this.wheelCount = wheelCount;
		this.maxCap = maxCap;
		this.maxLiters = maxLiters;
		
	}
	
	public void destroy() {
		System.out.println("Truck Explosion !!!");
	}
	
	@Override
	public void refuel() {
		// TODO Auto-generated method stub
		System.out.println("Refueling Car !!!");
	}
}
