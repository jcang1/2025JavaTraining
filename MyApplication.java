/**
* Seatwork 3
 */
package com.bpi.oop.main;

/**
 * 
 */
public class MyApplication {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Executable excel = new MSExcel();
		Executable word = new MSWord();
		
		runProgram(excel);
		runProgram(word);
		
		stopProgram(excel);
		stopProgram(word);
		
	}
	
	private static void runProgram(Executable executableProgram) {
		executableProgram.run();
		
	}
	
	private static void stopProgram(Executable executableProgram) {
		executableProgram.stop();
		
	}
	
}
