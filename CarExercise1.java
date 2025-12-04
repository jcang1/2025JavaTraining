/**
 * 
 */
package com.bpi.oop.main;

/**
 * 
 */
public class CarExercise1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car kotse = new Car();
		kotse.setBrand("Honda");
		kotse.setModel("Civic");
		kotse.setReleaseYear(2022);
		kotse.displayInput();
		
		Car kotse2 = new Car();
		kotse2.setBrand("Toyota");
		kotse2.setModel("Vios");
		kotse2.setReleaseYear(2022);
		kotse2.displayInput();
	}

}
