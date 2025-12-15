/**
 * 
 */
package activity;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;


/**
 * 
 */
public class M3_Activity1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> products = new ArrayList<>(Arrays.asList("Laptop", "Mouse", "Keyboard", "Monitor", "Printer"));


        // Print all products
        System.out.println("All Products:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }



        // Add new product "Webcam"
        products.add("Webcam");

        // Remove "Mouse"
        products.remove("Mouse");
        

        // Print all products
        System.out.println("After adding and removing products:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }
        
        final Scanner SCANNER = new Scanner(System.in);
        
        System.out.print("Enter product to search : ");
        String searchItem = SCANNER.nextLine();
        

        // Search for product
        if (products.contains(searchItem)) {
            System.out.println("Product found : " + searchItem);
        } else {
            System.out.println("Product not found!");
        }



        
        
        
        


		
	}

}
