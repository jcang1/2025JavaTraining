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
package com.bpi.m6groupproject.util;

import java.util.Scanner;

import com.bpi.m6groupproject.service.*;

import jakarta.persistence.EntityManager;

public class LibraryApplication {

	private BookServiceImpl bookServiceImpl;

	private static Boolean initScreen = true;

	private final EntityManager em;
	
	public LibraryApplication(EntityManager em) {
		this.em = em;
	}
	

	// Use one Scanner shared across the program
	private static final Scanner SCANNER = new Scanner(System.in);

	// Main Application Logic, call this in your Main.java
	public void start() {
		bookServiceImpl = new BookServiceImpl(em);
		System.out.println();
		MenuChoice userChoice;
		do {
			userChoice = mainMenu();

			switch (userChoice.getMenuChoice()) {
			case "1":
				bookServiceImpl.displayAllBooks();
				break;
			case "2":
				bookServiceImpl.displayAllAvailableBooks();
				break;
			case "3":
				bookServiceImpl.displayAllBorrowedBooks();
				break;
			case "4":
				bookServiceImpl.borrowBook();
				break;
			case "5":
				bookServiceImpl.returnBook();
				break;
			case "6":
				bookServiceImpl.addBook();
				break;
			case "7":
				bookServiceImpl.removeBook();
				break;
			case "8":
				bookServiceImpl.updateBook();
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
		if (initScreen == true) {
			initScreen = false;
		} else {
			System.out.println("Press \"Enter\" to go to main menu.");
			SCANNER.nextLine();
		}
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
