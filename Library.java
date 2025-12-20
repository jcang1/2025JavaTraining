package group_9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

public class Library {
	// can contain up to 5 books
	List<Book> book = new ArrayList<>();
	List<Loan> activeLoan = new ArrayList<>();

	private static final Scanner SCANNER = new Scanner(System.in);

	public void initialBooks() {
		book.add(new Book(0, "The 7 Habits of Highly Effective People", "Stephen Covey", false, false));
		book.add(new Book(1, "The Richest Man in Babylon", "George S. Clason", false, false));
		book.add(new Book(2, "Start With Why", "Simon Sinek", false, false));
		book.add(new Book(3, "The Laws of Human Nature", "Robert Greene", false, false));
		book.add(new Book(4, "Unreasonable Hospitality", "Will Guidara", false, false));
	}

	public void displayAllBooks() {
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("=========================================================");
		System.out.println("|Display All Books                                     ");
		int ctr = 0;
		for (Book b : book) {
			if (!b.getForDeletion()) {
				ctr++;
				System.out.println("=========================================================");
				System.out.println("|Book ID : " + b.getId());
				System.out.println("|Title   : " + b.getTitle());
				System.out.println("|Author  : " + b.getAuthor());
			}

		}
		System.out.println("+--------------------------------------------------------");
		System.out.println("|Total books : " + ctr);
		System.out.println("|End of report for all books                             ");
		System.out.println("=========================================================");
	}

	public void displayAllAvailableBooks() {
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("=========================================================");
		System.out.println("|Display All Available Books ");
		int ctr = 0;
		for (Book b : book) {
			if (!b.getForDeletion() && !b.getIsLoaned()) {
				ctr++;
				System.out.println("+--------------------------------------------------------");
				System.out.println("|Book ID : " + b.getId());
				System.out.println("|Title   : " + b.getTitle());
				System.out.println("|Author  : " + b.getAuthor());
			}

		}
		System.out.println("+--------------------------------------------------------");
		System.out.println("|Total Available books : " + ctr);
		System.out.println("|End of report for all available books");
		System.out.println("=========================================================");
	}

	public void displayAllBorrowedBooks() {
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("=========================================================");
		System.out.println("|Display All Borrowed Books  ");
		int ctr = 0;
		for (Book b : book) {
			if (!b.getForDeletion() && b.getIsLoaned()) {
				ctr++;
				System.out.println("+--------------------------------------------------------");
				System.out.println("|Book ID : " + b.getId());
				System.out.println("|Title   : " + b.getTitle());
				System.out.println("|Author  : " + b.getAuthor());
			}

		}
		System.out.println("+--------------------------------------------------------");
		System.out.println("|Total Borrowed books : " + ctr);
		System.out.println("|End of report for all Borrowed books ");
		System.out.println("=========================================================");

		// for (Loan loan : activeLoan) {
		// System.out.println("User: " + loan.toString());
		// }
	}

	public void borrowBook(User borrower, int bookID) {
		Boolean found = false;
		System.out.println(">>Validate if book is existing  ");
		for (Book b : book) {

			if (b.getId() == bookID) {
				found = true;
				if (!b.getForDeletion()) {
					System.out.println(">>>Confirming book is not deleted  ");
					System.out.println(">>>Validate if book is not loaned  ");

					if (!b.getIsLoaned()) {
						System.out.println(">>>>Confirming book is not loaned.");
						System.out.println(">>>>Processing...");
						b.setLoanStatus(true);

						Loan newLoan = new Loan(b, borrower);

						activeLoan.add(newLoan);
						System.out.println("+--------------------------------------------------------");

						// check if added in activeLoan list
						// System.out.println("activeLoan.size(): " + activeLoan.size());

						// System.out.println("activeLoan.indexOf(b) : " + activeLoan.indexOf(newLoan));
						// System.out.println("activeLoan entry : " +
						// activeLoan.get(activeLoan.indexOf(newLoan)));

						System.out.println(">>>>>Successful borrowing of book.");
					} else {
						System.out.println(">>>>>Book is loaned, try some other time.");
					}
				} else {

				}
				break;
			}
		} // end of for loop
		if (!found) {
			System.out.println(">>>>>Book ID not found.");
		}
		System.out.println("=========================================================");
	}

	public void returnBook(User borrower, int bookID) {
		returnBook(borrower, bookID, false);
	}

	public void returnBook(User borrower, int bookID, Boolean forDelete) {
		Boolean found = false;
		for (Book b : book) {
			if (b.getId() == bookID) {
				found = true;
				if (!b.getForDeletion() && b.getIsLoaned()) {
					System.out.println(">Processing...");

					// Find the exact loan by matching fields (no equals needed)
					int idx = -1;
					for (int i = 0; i < activeLoan.size(); i++) {
						Loan l = activeLoan.get(i);
						if (l.getUser().getLibraryID() == borrower.getLibraryID() && l.getBook().getId() == b.getId()) {
							idx = i;
							break;
						}
					}

					// System.out.println("r activeLoan.size(): " + activeLoan.size());
					// System.out.println("r found index: " + idx);

					if (idx >= 0) {
						Loan toRemove = activeLoan.remove(idx);
						// reset book state
						toRemove.getBook().setLoanStatus(false); // or setIsLoaned(false)
						// System.out.println("Removed from loan list");
					} else {
						System.out.println(">>No matching active loan found for this book ID.");
					}

					// System.out.println("r after returning activeLoan.size(): " +
					// activeLoan.size());
					if (forDelete) {
						System.out.println(">>>Removed from loan list.");
					} else {
						System.out.println(">>>Successful returning of book.");
					}
				} else if (b.getForDeletion()) {
					System.out.println(">>>>Cannot return deleted book.");
				} else {
					System.out.println(">>>>Book is not loaned.");
				}
				return;
			}
		}
		if (!found) {
			System.out.println(">>>>>Book ID not found.");
		}
		System.out.println("=========================================================");
	}

	public void addNewBooks(int newBookID, String newBookTitle, String newBookAuthor) {
		Boolean newBookIDValid = true;
		for (Book b : book) {
			if (b.getId() == newBookID) {
				newBookIDValid = false;
				System.out.println("|Conflict with bookID : " + newBookID);
				break;
			}
		} // end of for loop, checking of newBookIDValid

		if (newBookIDValid) {
			Book newBook = new Book(newBookID, newBookTitle, newBookAuthor, false, false);
			book.add(newBook);
			System.out.println("---------------------------------------------------------");
			System.out.println(">>The book has been added : ");
			System.out.println(">>ID : " + newBookID);
			System.out.println(">>Title : " + newBookTitle);
			System.out.println(">>Author : " + newBookAuthor);
			// step to sort the list of books when there is a new entry
			book.sort(Comparator.comparing(Book::getId, Comparator.nullsLast(Integer::compareTo)));
			// end of the sorting of the list of books
			System.out.println("=========================================================");
		}

	}

	public void removeBook(User rmBorrower, int rmBookID) {
		Boolean BookIDValid = false;
		for (Book b : book) {
			if (b.getId() == rmBookID) {
				BookIDValid = true;
				System.out.println(">BookID found in book list : " + rmBookID);

				// Find the exact loan by matching fields (no equals needed)
				int idx = -1;
				for (int i = 0; i < book.size(); i++) {
					Book r = book.get(i);
					if (r.getId() == b.getId()) {
						idx = i;
						break;
					}
				}

				if (idx >= 0) {
					System.out.println("Removing from book list... ");
					System.out.println("ID : " + b.getId());
					System.out.println("Title : " + b.getTitle());
					System.out.println("Author : " + b.getAuthor());
					System.out.println(" ");
					System.out.println("Press \"Y\" to proceed in deletion");
					String input1 = SCANNER.nextLine();
					String userChoice = input1.trim().toUpperCase();
					System.out.println("userChoice = " + userChoice);

					if (userChoice.equalsIgnoreCase("Y")) {
						returnBook(rmBorrower, rmBookID, true);
						book.remove(idx);
						System.out.println("---------------------------------------------------------");
						System.out.println("Book ID \"" + rmBookID + "\" has been removed.");
					} else {
						System.out.println("---------------------------------------------------------");
						System.out.println("Removal interrupted");
					}
				}
				break;
			}

		} // end of for loop
		if (!BookIDValid) {
			System.out.println("Book ID not found.");
		}
		System.out.println("=========================================================");
	}

	public Boolean checkBookID(int checkBookID) {
		Boolean BookIDValid = false;
		for (Book b : book) {
			if (b.getId() == checkBookID) {
				BookIDValid = true;
				break;
			}
		}
		if (!BookIDValid) {
			System.out.println("bookID is not existing: " + checkBookID);
			System.out.println("=========================================================");
			return false;
		} else {
			return true;
		}
	}

	public void updateBook(int updBookID, String newBookTitle, String newBookAuthor) {

		Boolean updBookIDValid = false;
		for (Book b : book) {
			if (b.getId() == updBookID) {
				updBookIDValid = true;
				System.out.println(">Valid bookID for update : " + updBookID);

				// Find the exact book by matching fields (no equals needed)
				int idx = -1;
				for (int i = 0; i < book.size(); i++) {
					Book r = book.get(i);
					if (r.getId() == b.getId()) {
						idx = i;
						break;
					}
				}

				if (b.getIsLoaned() == true) {
					System.out.println(">Book is loaned, cannot update.");
				} else {

					if (idx >= 0) {
						System.out.println("---------------------------------------------------------");
						System.out.println(">>Updating... ");
						System.out.println(">>>ID : " + b.getId());
						System.out.println(">>>>old value for Title : " + b.getTitle());
						System.out.println(">>>>new value for Title : " + newBookTitle);
						System.out.println(">>>>old value for Author : " + b.getAuthor());
						System.out.println(">>>>new value for Author : " + newBookAuthor);
						System.out.println(" ");
						System.out.println("Press \"Y\" to proceed in update");
						String input1 = SCANNER.nextLine();
						String userChoice = input1.trim().toUpperCase();
						System.out.println("userChoice = " + userChoice);

						if (userChoice.equalsIgnoreCase("Y")) {
							Book updBook = new Book(b.getId(), newBookTitle, newBookAuthor, false, false);
							book.set(idx, updBook);
							System.out.println("---------------------------------------------------------");
							System.out.println("Book ID \"" + b.getId() + "\" has been updated.");
						} else {
							System.out.println("---------------------------------------------------------");
							System.out.println("Update interrupted");
						}

					}

				}

				break;
			}
		}

		if (!updBookIDValid) {
			System.out.println("bookID is not existing: " + updBookID);
		}
		System.out.println("=========================================================");

	}

}
