package com.bpi.oop.main.m2.activity5;

public abstract class Program {
	
	private String name;
	private boolean isRunning = false;
	
	public Program() {
		
	}
	
	public Program(String name) {
		this.name = name;
	}
	
	abstract void run();
	abstract void stop();
	
	public void setIsRunning() {
		this.isRunning = true;
	}
	
	public void setIsStopping() {
		this.isRunning = false;
	}
	
	public boolean getIsRunning() {
		return isRunning;
	}
   
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
}


