/**
 * 
 */
package com.bpi.oop.main;

/**
 * 
 */
public class Car {

	/**
	 * 
	 */
	// Declaration of Attributes
	String brand;
	String model;
	int releaseYear;
	// End declaration of Attributes


    public Car(String brand, String model, int releaseYear) {
        this.brand = brand;
        this.model = model;
        this.releaseYear = releaseYear;
    }

	// Getter & Setter for brand
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Getter & Setter for model
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    // Getter & Setter for releaseYear
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

	void displayInput() {
		System.out.println("Under the brand " + this.getBrand() + ", you have chosen the model " + this.getModel() + ". It was released on " + this.getReleaseYear() + ". Enjoy!");
	}
    
	public Car() {
		// TODO Auto-generated constructor stub
	}

}
