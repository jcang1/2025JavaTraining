/**
 * 
 */
package activity1;

/**
 * 
 */
import java.util.Scanner;

/**
 * 
 */
public class Menu extends Services{
    // Use one Scanner shared across the program
    private static final Scanner SCANNER = new Scanner(System.in);
    private String userChoice;
    private int counter = 0;
    
    
    public void displayMenu() {
    	if (counter == 0) {
    	} else {
    		System.out.println(" ");
    		System.out.println("Press enter to continue!");
        	SCANNER.nextLine();
    	}
    	counter++;
    	
    	System.out.println("==================================================");
    	System.out.println("==  CASHIER PRODUCT QUEUE                       ==");
    	System.out.println("==  (LinkedListQueue)                           ==");
    	System.out.println("==================================================");
    	System.out.println("==  Select and option:                          ==");
    	System.out.println("==----------------------------------------------==");
    	System.out.println("==  1. Add a product                            ==");
    	System.out.println("==  2. Process next product                     ==");
    	System.out.println("==  3. Check number of products                 ==");
    	System.out.println("==  4. View total bill                          ==");
    	System.out.println("==  5. Exit                                     ==");
    	System.out.println("==================================================");
    	
    	String menuChoice = SCANNER.nextLine();
    	
    	this.userChoice = menuChoice;
    }
    
    public String getMenuChoice() {
    	return this.userChoice;
    }
    
    
    
    @Override
    void option1() {
    	System.out.print("Enter product to add : ");
    	String input1 = SCANNER.nextLine();
    	String productName = input1;
    	
    	System.out.print("Enter product price : ");
    	String input2 = SCANNER.nextLine();		
		Double productPrice = Double.parseDouble(input2);
		
    	Products productAdd = new Products(productName,productPrice);
		productsQueue.add(productAdd);
    }
    
    public void exiting() {
    	System.out.println("Exiting...");
    }
	
    
}
