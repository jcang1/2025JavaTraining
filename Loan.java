/**
 * 
 */
package group_9;


public class Loan {
	
	private User user;
	private Book book;	
	

    public Loan(Book book, User user) {
    	this.book = book;
    	this.user = user;        
    }
    
    public Book getBook() {
    	return book;
    }
    
    public User getUser() {
    	return user;
    }

    //@Override
    //public String toString() {
    //return "Loan{user=" + user.getName() +
    //       ", book=" + book.getTitle() + "}";
    //}


    /*
    public void borrowBook(int userID, int toBorrowBookID) {
        boolean valid = false;
        // Validate book ID
        for (int j = 0; j < 5; j++) {
            if (book.getBookID(j) == toBorrowBookID) {
                valid = true;
                System.out.println("Valid book ID");
                System.out.println("Confirming book availability");

                if (!book.getBookLoanStatus(j)) {
                    System.out.println(book.getBookTitle(j) + " is Available");
                    // Mark as borrowed via Book API
                    book.setBorrower(j, userID);
                    book.setBookLoanStatus(j, true);
                    System.out.println("Book has been loaned to user ID " + userID);
                } else {
                    System.out.println(book.getBookTitle(j) + " is not available");
                }
                break;
            }
        }

        if (!valid) {
            System.out.println("Invalid book ID");
        }
    }

    // --- Return logic moved from Book; now public as requested ---
    public void setReturn(int userBorrowerID, int returnBookID) {
    	if (book.getBookLoanStatus(returnBookID)) {
    		System.out.println("Confirming book is loaned");
    		
    		if (book.getBorrowerID(returnBookID) == userBorrowerID) {
                System.out.println("Returner and borrower is the same.");
            } else {
                System.out.println("Alert: the returner is not the original borrower!");
            }
    		
    		System.out.println("Tagging book as returned");
            book.setBorrower(returnBookID, 0);
            book.setBookLoanStatus(returnBookID, false);
            System.out.println("Book \"" + book.getBookTitle(returnBookID) + "\" has been returned.");
    		
    	} else {
    		System.out.println("Book is not loaned!");
    	}
        
    }
*/
	
}

