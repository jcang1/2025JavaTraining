package group_9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Library {
	// can contain up to 5 books
	List<Book> book = new ArrayList<>();
	List<Loan> activeLoan = new ArrayList<>();

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
		System.out.println("============================");
		System.out.println("Display All Books");
		int ctr = 0;
		for (Book b : book) {
			if (!b.getForDeletion()) {
				ctr++;
				System.out.println("----------------------------");
				System.out.println("Book ID : " + b.getId());
				System.out.println("Title   : " + b.getTitle());
				System.out.println("Author  : " + b.getAuthor());
			}

		}
		System.out.println("----------------------------");
		System.out.println("Total books : " + ctr);
		System.out.println("End of report for all books ");
		System.out.println("============================");
	}

	public void displayAllAvailableBooks() {
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("============================");
		System.out.println("Display All Available Books ");
		int ctr = 0;
		for (Book b : book) {
			if (!b.getForDeletion() && !b.getIsLoaned()) {
				ctr++;
				System.out.println("-------------------------------------");
				System.out.println("Book ID : " + b.getId());
				System.out.println("Title   : " + b.getTitle());
				System.out.println("Author  : " + b.getAuthor());
			}

		}
		System.out.println("-------------------------------------");
		System.out.println("Total Available books : " + ctr);
		System.out.println("End of report for all available books");
		System.out.println("=====================================");
	}

	public void displayAllBorrowedBooks() {
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("============================");
		System.out.println("Display All Borrowed Books  ");
		int ctr = 0;
		for (Book b : book) {
			if (!b.getForDeletion() && b.getIsLoaned()) {
				ctr++;
				System.out.println("-------------------------------------");
				System.out.println("Book ID : " + b.getId());
				System.out.println("Title   : " + b.getTitle());
				System.out.println("Author  : " + b.getAuthor());
			}

		}
		System.out.println("-------------------------------------");
		System.out.println("Total Borrowed books : " + ctr);
		System.out.println("End of report for all Borrowed books ");
		System.out.println("=====================================");

		// for (Loan loan : activeLoan) {
		// System.out.println("User: " + loan.toString());
		// }
	}

	public void borrowBook(User borrower, int bookID) {

		for (Book b : book) {
			System.out.println("Validate if book is not deleted  ");
			if (b.getId() == bookID) {
				if (!b.getForDeletion()) {
					System.out.println("Confirming book is not deleted  ");
					System.out.println("Validate if book is not loaned  ");

					if (!b.getIsLoaned()) {
						System.out.println("Confirming book is not loaned.");
						System.out.println("Processing...");
						b.setLoanStatus(true);

						Loan newLoan = new Loan(b, borrower);

						activeLoan.add(newLoan);

						// check if added in activeLoan list
						System.out.println("activeLoan.size(): " + activeLoan.size());

						System.out.println("activeLoan.indexOf(b) : " + activeLoan.indexOf(newLoan));
						System.out.println("activeLoan entry : " + activeLoan.get(activeLoan.indexOf(newLoan)));

						System.out.println("Successful borrowing of book.");
					} else {
						System.out.println("Book is loaned, try some other time.");
					}
				} else {
					System.out.println("Cannot borrow deleted book.");
				}
				break;
			}
		} // end of for loop
	}

	public void returnBook(User borrower, int bookID) {
		for (Book b : book) {
			if (b.getId() == bookID) {
				if (!b.getForDeletion() && b.getIsLoaned()) {
					System.out.println("Processing...");

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
						System.out.println("Removed loan: " + toRemove);
					} else {
						System.out.println("No matching active loan found for this user/book.");
					}

					// System.out.println("r after returning activeLoan.size(): " +
					// activeLoan.size());
					System.out.println("Successful returning of book.");
				} else if (b.getForDeletion()) {
					System.out.println("Cannot return deleted book.");
				} else {
					System.out.println("Book is not loaned.");
				}
				return;
			}
		}
		System.out.println("Book ID not found.");
	}

	public void addNewBooks(int newBookID, String newBookTitle, String newBookAuthor) {
		Boolean newBookIDValid = true;
		for (Book b : book) {
			if (b.getId() == newBookID) {
				newBookIDValid = false;
				System.out.println("Conflict with bookID : " + newBookID);
				break;
			}
		} // end of for loop, checking of newBookIDValid

		if (newBookIDValid) {
			Book newBook = new Book(newBookID, newBookTitle, newBookAuthor, false, false);
			book.add(newBook);
			System.out.println("The book has been added : ");
			System.out.println("ID : " + newBookID);
			System.out.println("Title : " + newBookTitle);
			System.out.println("Author : " + newBookAuthor);
		}

	}

	public void removeBook(User rmBorrower, int rmBookID) {
		returnBook(rmBorrower, rmBookID);

		for (Book b : book) {
			if (b.getId() == rmBookID) {

				System.out.println("BookID found : " + rmBookID);

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
					System.out.println("Removing... ");
					System.out.println("ID : " + b.getId());
					System.out.println("Title : " + b.getTitle());
					System.out.println("Author : " + b.getAuthor());
					book.remove(idx);
					System.out.println("Removed");
				}
				break;
			}
		} // end of for loop

	}

	public void updateBook(int updBookID, String newBookTitle, String newBookAuthor) {

		Boolean updBookIDValid = false;
		for (Book b : book) {
			if (b.getId() == updBookID) {
				updBookIDValid = true;
				System.out.println("Existing bookID : " + updBookID);

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
					System.out.println("Book is loaned, cannot update.");
				} else {

					if (idx >= 0) {
						System.out.println("Updating... ");
						System.out.println("ID : " + b.getId());
						System.out.println("old value for Title : " + b.getTitle());
						System.out.println("new value for Title : " + newBookTitle);
						System.out.println("old value for Author : " + b.getAuthor());
						System.out.println("new value for Author : " + newBookAuthor);
						Book updBook = new Book(b.getId(), newBookTitle, newBookAuthor, false, false);
						book.set(idx, updBook);

						System.out.println("Updated");
					}

				}

				break;
			}
		}

		if (!updBookIDValid) {
			System.out.println("bookID is not existing: " + updBookID);
		}

	}

}
