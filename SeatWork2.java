/**
 * 
 */
package com.bpi.oop.main;

/**
 * 
 */
public class SeatWork2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WhiteBoard wb = new WhiteBoard();
		int resultReceiver = wb.addNumbers(5, 3); //if you check the class WhiteBoard, it returns total. The value is then saved to resultReceiver.
		System.out.println("The total is: " + String.valueOf(resultReceiver));
	}

}
