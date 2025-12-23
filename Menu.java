/**
 * 
 */
package activity3;
import java.util.Scanner;

/**
 * 
 */
public class Menu extends Services{
    // Use one Scanner shared across the program
    private static final Scanner SCANNER = new Scanner(System.in);
    private String userChoice;
    Products products = new Products();
    
    public void initialLoad() {
    	products.productList();
    }
    
    public void displayMenu() {
    	System.out.println("==================================================");
    	System.out.println("==  Select and option:                          ==");
    	System.out.println("==----------------------------------------------==");
    	System.out.println("==  1. Search a product                         ==");
    	System.out.println("==  2. Add a product                            ==");
    	System.out.println("==  3. Print all products and prices            ==");
    	System.out.println("==  4. Find the cheapest product                ==");
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
    	System.out.print("Enter product to search : ");
        String item = SCANNER.nextLine();
        products.searchItem(item);
    }
    
    @Override
    void option2() {
    	System.out.print("Enter product to add : ");
    	String input1 = SCANNER.nextLine();
    	
    	System.out.print("Enter product price : ");
    	String input2 = SCANNER.nextLine();
    	Double itemPrice = Double.parseDouble(input2);
    	
    	products.addItem(input1, itemPrice);
    }
	
    @Override
    void option3() {
    	products.displayProductList();
    }
    
    @Override
    void option4() {
    	products.cheapestProducts();
    }
    
    @Override
    void option5() {
    	System.out.println("Exiting...");
    }
    
}
