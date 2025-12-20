/*
 * 1. Upon application start, ask user to create one User
 * 2. Create one Library object
 * 3. Initialize 5 Book objects and add it to all Library slots
 * 4. Display options:
 * 
 * - [1] Display All Books
 * - [2] Display Available Books
 * - [3] Display All Borrowed Books
 * - [4] Borrow Book
 * - [5] Return Book
 * - [6] Exit
 * 
 * - user selects the number of the option
 * ===============================================
 * 
 *	 [1] Display All Books
 * - Display all Books (ID, Title and Author) regardless if there is a Loan existing for that Book.
 *   
 *   [2] Display Available Books
 * - Display Books that do not have a Loan slot
 * 
 *   [3] Display All Borrowed Books 
 * - Display Books that have a Loan equivalent.
 * - Display the Book title and the User name of borrower
 *   
 *	 [4] Borrow Book
 * - Displays all available books and User selects what book to borrow
 * - Create a Loan object, set Loan id set Book and set User to current user
 * 
 * 	 [5] Return Book
 * - Display all Loans, user selects the Loan and removes that from the slot
 * 
 *   [6] Exit
 * - Stops the program  
 * */
package group_9;

import java.util.Scanner;

public class LibraryApplication {

	private User user;
	private Library library;
	//private Loan loan;

	// Use one Scanner shared across the program
	private static final Scanner SCANNER = new Scanner(System.in);

	// Main Application Logic, call this in your Main.java
	public void start() {
		// initial user creation
		this.user = new User();

		System.out.println("Please enter your library ID #");
		String input1 = SCANNER.nextLine();

		int userInput1 = Integer.parseInt(input1); // read input and convert to integer
		// for user ID

		System.out.println("Please enter your user name #");
		String input2 = SCANNER.nextLine();

		// for user name
		user.setUser(userInput1, input2);

		// initial library creation
		this.library = new Library();
		library.initialBooks();

		// initial loan creation
		// **this.loan = new Loan(this.user, this.library);

		// add code here
		MenuChoice userChoice;
		do {
			userChoice = mainMenu();

			switch (userChoice.getMenuChoice()) {
			case "1":
				library.displayAllBooks();
				break;
			case "2":
				library.displayAllAvailableBooks();
				break;
			case "3":

				library.displayAllBorrowedBooks();
				break;
			case "4":
				System.out.println("Borrowing book");
				System.out.println("Please enter book ID");
				String input3 = SCANNER.nextLine();
				

				int userInput3 = Integer.parseInt(input3); // read input and convert to integer
				// System.out.println("**Investigation");
				// System.out.println("userInput1: " + userInput1);
				// System.out.println("userInput3: " + userInput3);
				// **loan.borrowBook(user.getLibraryID(), userInput3);
				library.borrowBook(user, userInput3);
				break;

			case "5":

				System.out.println("Returning book");
				System.out.println("Please enter book ID");
				String input4 = SCANNER.nextLine();

				int userInput4 = Integer.parseInt(input4); // read input and convert to integer

				// **loan.setReturn(user.getLibraryID(), userInput4);

				library.returnBook(user, userInput4);

				break;

			case "6":
				System.out.println("Adding book");
				System.out.println("Please enter book ID");
				String input5 = SCANNER.nextLine();
				int userInput5 = Integer.parseInt(input5);
				
				if (library.checkBookID(userInput5)) {
					break;
				}

				System.out.println("Please enter book Title");
				String input6 = SCANNER.nextLine();

				System.out.println("Please enter book Author");
				String input7 = SCANNER.nextLine();

				library.addNewBooks(userInput5, input6, input7);

				break;
			case "7":
				System.out.println("Remove book");
				System.out.println("Please enter book ID");
				String input8 = SCANNER.nextLine();
				int userInput8 = Integer.parseInt(input8);

				library.removeBook(user, userInput8);

				break;

			case "8":
				System.out.println("Update book");
				System.out.println("Please enter book ID");
				String input9 = SCANNER.nextLine();
				int userInput9 = Integer.parseInt(input9);
				
				if (!library.checkBookID(userInput9)) {
					break;
				}
				
				System.out.println("Please enter book Title");
				String input10 = SCANNER.nextLine();

				System.out.println("Please enter book Author");
				String input11 = SCANNER.nextLine();

				library.updateBook(userInput9, input10, input11);

				break;

			case "0":
				System.out.println("Exiting.........                ");

				for (int i = 0; i <= 100; i++) {
					System.out.println();
				}
				System.out.println("Thank you for using     ");
				System.out.println("The Enhanced Library System!");
				for (int i = 0; i <= 20; i++) {
					System.out.println();
				}
				System.out.println("Program terminated!             ");
				break;
			default:
				System.out.println("Invalid Choice. Please choose from 1, 2, 3, 4, 5, 6, 7, 8, 0. Thank you");
			}
		} while (!"0".equals(userChoice.getMenuChoice()));

	}

	// add code here

	private static class MenuChoice {
		final String mChoice;

		// Constructor name must match the class name
		MenuChoice(String mChoice) {
			this.mChoice = mChoice;
		}

		private String getMenuChoice() {
			return mChoice;
		}
	}

	private static MenuChoice mainMenu() {
		System.out.println("Press Enter to go to main menu.");
		String main = SCANNER.nextLine();
		
		System.out.println("=========================================================");
		System.out.println("|                ENHANCED LIBRARY SYSTEM                |");
		System.out.println("+-------------------------------------------------------+");
		System.out.println("|[1] Display All Books                                  |");
		System.out.println("|[2] Display Available Books                            |");
		System.out.println("|[3] Display All Borrowed Books                         |");
		System.out.println("|[4] Borrow Book                                        |");
		System.out.println("|[5] Return Book                                        |");
		System.out.println("|[6] Add Book                                           |");
		System.out.println("|[7] Remove Book                                        |");
		System.out.println("|[8] Update Book                                        |");
		System.out.println("|[0] Exit                                               |");
		System.out.println("=========================================================");
		System.out.print("Enter Choice:                     ");

		String userChoice = SCANNER.nextLine();
		// Normalize input here (optional, can also do it in main)
		userChoice = userChoice.trim().toUpperCase();

		return new MenuChoice(userChoice);
	}

}
