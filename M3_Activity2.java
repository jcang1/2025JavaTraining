/**
 * 
 */
package activity;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Set;


/**
 * 
 */
public class M3_Activity2 {
	
    // Use one Scanner shared across the program
    private static final Scanner SCANNER = new Scanner(System.in);
	
		/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> products = new HashSet<>(Arrays.asList("Laptop", "Mouse", "Keyboard", "Monitor", "Printer"));
		//Invokes the main menu
		MenuChoice userChoice;
		
		do {
        	userChoice = mainMenu();
        	
        	switch (userChoice.getMenuChoice()) {
            	case "1": 
                    System.out.print("Enter product to search : ");
                    String searchItem = SCANNER.nextLine();
                    

                    // Search for product
                    if (products.contains(searchItem)) {
                        System.out.println("Product found : " + searchItem);
                    } else {
                        System.out.println("Product not found!");
                    }           		
            		
            	break;
            	case "2": 
                	System.out.print("Enter product name to add : ");
                	String addItem = SCANNER.nextLine();
                	products.add(addItem);
                	System.out.println("Product Added : " + addItem);
            	break;
            	case "3": 
            		System.out.println("All products");

                    for (String product : products) {
                        System.out.println(product);
                    }
                    
                    System.out.println("Total unique products: " + products.size());
            	
            	break;
            	case "4": System.out.println("Exiting...                      ");
            	break;
            	default:  System.out.println("Invalid Choice. Please choose from 1, 2, 3, 4. Thank you");
        	}        	
		} while (!"4".equals(userChoice.getMenuChoice()));	
		
	}

    private static class MenuChoice {
        final String mChoice;

        // Constructor name must match the class name
        MenuChoice(String mChoice) {
            this.mChoice = mChoice;
        }

        private String getMenuChoice() { return mChoice; }
    }
    
	private static MenuChoice mainMenu() {
		System.out.println("==================================");
		System.out.println("Select an option:                 ");
		System.out.println("[1] Search a product              ");
		System.out.println("[2] Add a product                 ");
		System.out.println("[3] Print all products and count  ");
		System.out.println("[4] Exit                          ");
		System.out.println("==================================");
		System.out.print("Enter Choice:                     ");
		
        String userChoice = SCANNER.nextLine();
        
        return new MenuChoice(userChoice);
	}	
	
	
}
