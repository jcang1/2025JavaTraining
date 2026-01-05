package activity1;

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
		menu.exiting();
	}

}
