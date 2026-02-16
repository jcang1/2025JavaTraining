package com.bpi.m7groupproject.service;

import java.util.List;
import java.util.stream.Collectors;

import com.bpi.m7groupproject.dto.BookDTO;
import com.bpi.m7groupproject.dto.LoanDTO;
import com.bpi.m7groupproject.model.*;
import com.bpi.m7groupproject.repository.*;

import jakarta.persistence.EntityManager;

public class BookServiceImplAPI implements BookService {
	private final EntityManager em;

	private final BookRepository bookRepository;
	private final UserRepository userRepository;
	private final LoanRepository loanRepository;

	public BookServiceImplAPI(EntityManager em) {
		this.em = em;
		this.bookRepository = new BookRepository(em);
		this.userRepository = new UserRepository(em);
		this.loanRepository = new LoanRepository(em);
	}

	public List<BookDTO> listAllBooks() {
		return bookRepository.findAll().stream().map(Book::toDTO).collect(Collectors.toList());
	}

	public List<BookDTO> listAllAvailableBooks() {
		return bookRepository.findAllAvailable().stream().map(Book::toDTO).collect(Collectors.toList());
	}

	public List<BookDTO> listAllBorrowedBooks() {
		return bookRepository.findAllNotAvailable().stream().map(Book::toDTO).collect(Collectors.toList());
	}

	public String borrowBook(LoanDTO loanDTO) {
		Long borrowUserID = loanDTO.getUserId();
		Long borrowBookID = loanDTO.getBookId();
		LoanDTO newLoanDTO = new LoanDTO();
		String message = null;
		try {
			if (userRepository.findById(borrowUserID).equals(null)) {
				throw new Exception("User ID not found!");
			} else {
				Long borrowedByUser = loanRepository.countLoanedByUser(borrowUserID);
				Long max5 = 5L;

				if (borrowedByUser == max5) {
					throw new Exception(
							"User has reached the loan limit of 5 books \n Kindly return other loaned books.");
				}
			}
			if (bookRepository.findById(borrowBookID).equals(null)) {
				throw new Exception("Book ID not found!");
			} else {
				System.out.println("Book is found");
			}

			if (bookRepository.findById(borrowBookID).getBookIsAvailable().equals(false)) {
				throw new Exception("Book is Loaned!");
			} else {
				System.out.println("Book is available");
			}

			try {
				em.getTransaction().begin();
				newLoanDTO.setUserId(borrowUserID);
				newLoanDTO.setBookId(borrowBookID);
				loanRepository.save(newLoanDTO.toEntity(em));
				Book borrowBook = bookRepository.findById(borrowBookID);
				borrowBook.setBookIsAvailable(false);
				bookRepository.save(borrowBook);

				em.flush();
				em.getTransaction().commit();
				em.clear();
				// newLoanDTO =
				// loanRepository.findById(loanRepository.findByUserIDAndBookID(borrowUserID,
				// borrowBookID));
				message = "Book ID (" + borrowBookID + ") has been loaned " + " to UserID (" + borrowUserID + ").";
			} catch (Exception e) {
				try {
					em.getTransaction().rollback();
				} catch (Exception rbEx) {
					rbEx.printStackTrace();
				}
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	public String returnBook(LoanDTO loanDTO) {
		// TODO
		Long borrowUserID = loanDTO.getUserId();
		Long borrowBookID = loanDTO.getBookId();
		String message = null;
		try {
			Long returnUserID = borrowUserID;

			if (userRepository.findById(returnUserID).equals(null)) {
				throw new Exception("User ID not found!");
			}

			Long returnBookID = borrowBookID;

			if (bookRepository.findById(returnBookID).equals(null)) {
				throw new Exception("Book ID not found!");
			}

			if (bookRepository.findById(returnBookID).getBookIsAvailable().equals(true)) {
				throw new Exception("Book is not Loaned! Check inventory and validate the book.");
			}

			// Loan returnLoan = new Loan();
			// returnLoan.setLoan(userRepository.findById(returnUserID),
			// bookRepository.findById(returnBookID));

			// loanRepository.save(newLoan);
			// check if the userId and bookID combination is in loan table,
			// else book might be returned by a different user

			Long loanID = loanRepository.findByUserIDAndBookID(returnUserID, returnBookID);

			if (loanID == null) {
				System.out.println("User ID and Book ID is not matched.");
				System.out.println("Book is probably loaned to another user.");
				System.out.println(
						"Will still process the book for returning since it is still property of the library.");
				loanID = loanRepository.findByBookID(returnBookID);
			}

			// deleting records from Loan
			loanRepository.deleteById(loanID);

			// updating book availability
			Book returnBook = bookRepository.findById(returnBookID);
			em.getTransaction().begin();
			returnBook.setBookIsAvailable(true);
			bookRepository.save(returnBook);

			em.flush();
			em.getTransaction().commit();
			em.clear();
			message = "BookID " + returnBookID + " has been returned. " + " Title: " + returnBook.getTitle();
		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				try {
					em.getTransaction().rollback();
				} catch (Exception rbEx) {
					rbEx.printStackTrace();
				}
			}

			e.printStackTrace();
		}
		return message;
	}

	// Add book if BookID
	public BookDTO addBook(BookDTO bookDTO) {
		Book saved = null;
		BookDTO savedDTO = null;

		em.getTransaction().begin();
		try {
			bookDTO.setIsAvailable(true);
			saved = bookRepository.save(bookDTO.toEntity());
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				try {
					em.getTransaction().rollback();
				} catch (Exception rbEx) {
					rbEx.printStackTrace();
				}
			}

			e.printStackTrace();
		}
		savedDTO = saved.toDTO();
		return savedDTO;
	}

	public String removeBook(Long delBookID) {

		String message = null;
		try {
			em.getTransaction().begin();

			if (loanRepository.findByBookID(delBookID) != null) {
				if (loanRepository.findByBookID(delBookID) > 0) {
					System.out.println("Book is loaned, removing from loan DB");
					loanRepository.delete(loanRepository.findById(loanRepository.findByBookID(delBookID)));
				}
			}
			

			bookRepository.deleteById(delBookID);

			em.flush();
			em.getTransaction().commit();
			em.clear();
			message = "BookID (" + delBookID + ") has been removed";

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				try {
					em.getTransaction().rollback();
				} catch (Exception rbEx) {
					rbEx.printStackTrace();
				}
			}

			e.printStackTrace();
		}
		return message;
	}

	public BookDTO updateBook(BookDTO bookDTO) {
		Long id = bookDTO.getId(); // read directly from DTO
		Book saved = null;
		BookDTO savedDTO = null;
		
		if (id == null) {
			System.out.println("Book ID is mandatory for update");
		}
		
		Book existing = null;
		if (id != null) { // only check when client sent an ID
			existing = bookRepository.findById(id); // may be null
			if (existing == null) { // ID was supplied but doesn't exist
				System.out.println("Book ID should be existing to update");
			}
		}
		
		if (existing.getBookIsAvailable() == false) {
			System.out.println("Book is loaned. Update not allowed.");
		}
	
		if (existing != null && existing.getBookIsAvailable() == true) {
			em.getTransaction().begin();
			try {
				saved = bookRepository.save(bookDTO.toEntity());
				em.getTransaction().commit();
			} catch (Exception e) {
				if (em.getTransaction().isActive()) {
					try {
						em.getTransaction().rollback();
					} catch (Exception rbEx) {
						rbEx.printStackTrace();
					}
				}

				e.printStackTrace();
			}
			savedDTO = saved.toDTO();
		}

		return savedDTO;
	}

	@Override
	public void displayAllBooks() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayAllAvailableBooks() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayAllBorrowedBooks() {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrowBook() {
		// TODO Auto-generated method stub

	}

	@Override
	public void returnBook() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addBook() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBook() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeBook() {
		// TODO Auto-generated method stub

	}

}
