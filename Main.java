/**
 * M3_ACTIVITY3
 * Goal: Practice Map Operations
 * Task: 
 * 1.	Create a Map of products with 5 initial records
 * (Key = product name, Value = product price)
 * 2.  Display a menu repeatedly until user chooses to exit.
 * 3. Create implementation for each options.
 */
package activity3;

/**
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu menu = new Menu();
		menu.initialLoad();
		
		do {
			menu.displayMenu();
			
			switch(menu.getMenuChoice()) {
			case "1":
				menu.option1();
				break;
			case "2":
				menu.option2();
				break;
			case "3":
				menu.option3();
				break;
			case "4":
				menu.option4();
				break;
			case "5":
				menu.option5();
				break;
			default: 
				menu.defaultOption();
			}
		} while (!"5".equals(menu.getMenuChoice()));
		
	}

}
