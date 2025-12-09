/**
 * 
 */
package com.bpi.oop.main.exercise2;

import com.bpi.oop.main.m2.activity5.Program;

/**
 * 
 */
public class MainApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Truck optimus = new Truck();		
		Car   bee     = new Car();
		destroyVehicle(optimus);
		destroyVehicle(bee);
		
	}

	private static void destroyVehicle(Vehicle ride) {
		
		ride.destroy();
	}

}
