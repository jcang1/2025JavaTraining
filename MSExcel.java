/**
 * 
 */
package com.bpi.oop.main.m2.activity5;

/**
 * 
 */
public class MSExcel extends Program {
	
	public MSExcel(String name) {
		setName("MSExcel");
	}
	
	@Override
	public void run() {
		String message = "Opening MS Excel...";
      setIsRunning();
		System.out.println(message);
	}
	
	@Override
	public void stop() {
		String message = "Stopping MS Excel...";
		setIsStopping();
		
		System.out.println(message);
	}
}
