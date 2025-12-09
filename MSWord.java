package com.bpi.oop.main.m2.activity5;



public class MSWord extends Program{
	
	public MSWord(String name) {
		setName("MSWord");
	}
	
	@Override
	public void run() {
		System.out.println("Opening MS Word...");
		setIsRunning();
	}
	
	@Override
	public void stop() {
		System.out.println("Stopping MS Word...");
		setIsStopping();
	}
	

}
