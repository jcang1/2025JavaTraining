package com.bpi.m6groupproject.service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.bpi.m6groupproject.model.*;
import com.bpi.m6groupproject.model.dto.BookDTO;
import com.bpi.m6groupproject.repository.*;

import jakarta.persistence.EntityManager;

public class BookServiceImpl implements BookService {
	private static final Scanner SCANNER = new Scanner(System.in);

	private final EntityManager em;

	private final BookRepository bookRepository;
	private final UserRepository userRepository;
	private final LoanRepository loanRepository;

	public BookServiceImpl(EntityManager em) {
		this.em = em;
		this.bookRepository = new BookRepository(em);
		this.userRepository = new UserRepository(em);
		this.loanRepository = new LoanRepository(em);
	}

	// helper to avoid "null" text
	private static String nullSafe(String s) {
		return s == null ? "" : s;
	}

	@Override
	public void displayAllBooks() {

		List<BookDTO> dtos = bookRepository.findAll().stream().map(Book::toDTO).toList();

		String header = "id|title|author|isAvailable";

		String body = dtos
				.stream().map(d -> String.join("|", String.valueOf(d.getId()), nullSafe(d.getTitle()),
						nullSafe(d.getAuthor()), String.valueOf(d.getIsAvailable())))
				.collect(Collectors.joining(System.lineSeparator()));

		int total = dtos.size();

		System.out.println(header);
		System.out.println(body);
		System.out.println("Total books: " + total);
	}

	@Override
	public void displayAllAvailableBooks() {

		List<BookDTO> dtos = bookRepository.findAllAvailable().stream().map(Book::toDTO).toList();

		String header = "id|title|author|isAvailable";

		String body = dtos
				.stream().map(d -> String.join("|", String.valueOf(d.getId()), nullSafe(d.getTitle()),
						nullSafe(d.getAuthor()), String.valueOf(d.getIsAvailable())))
				.collect(Collectors.joining(System.lineSeparator()));

		int total = dtos.size();

		System.out.println(header);
		System.out.println(body);
		System.out.println("Total Available books: " + total);
	}

	@Override
	public void displayAllBorrowedBooks() {

		List<BookDTO> dtos = bookRepository.findAllNotAvailable().stream().map(Book::toDTO).toList();

		String header = "id|title|author|isAvailable";

		String body = dtos
				.stream().map(d -> String.join("|", String.valueOf(d.getId()), nullSafe(d.getTitle()),
						nullSafe(d.getAuthor()), String.valueOf(d.getIsAvailable())))
				.collect(Collectors.joining(System.lineSeparator()));

		int total = dtos.size();

		System.out.println(header);
		System.out.println(body);
		System.out.println("Total Borrowed books: " + total);
	}

	@Override
	public void borrowBook() {
		// TODO
		System.out.println("Please enter User ID");
		try {
			Long borrowUserID = Long.parseLong(SCANNER.nextLine());

			if (userRepository.findById(borrowUserID).equals(null)) {
				throw new Exception("User ID not found!");
			} else {
				Long borrowedByUser = loanRepository.countLoanedByUser(borrowUserID);
				Long max5 = 5L;

				if (borrowedByUser == max5) {
					throw new Exception(
							"User has reaced the loan limit of 5 books \n Kindly return other loaned books.");
				}

			}

			System.out.println("Please enter Book ID");
			Long borrowBookID = Long.parseLong(SCANNER.nextLine());

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

			Book borrowBook = bookRepository.findById(borrowBookID);
			User borrower = userRepository.findById(borrowUserID);
			Loan newLoan = new Loan();

			try {
				em.getTransaction().begin();
				newLoan.setLoan(borrower, borrowBook);
				loanRepository.save(newLoan);
				borrowBook.setBookIsAvailable(false);
				bookRepository.save(borrowBook);

				em.flush();
				em.getTransaction().commit();
				em.clear();
				System.out.println(
						"Book " + borrowBook.getTitle() + " has been loaned " + " to UserID" + borrower.getId());
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

	}

	@Override
	public void returnBook() {
		// TODO
		System.out.println("Please enter User ID");
		try {
			Long returnUserID = Long.parseLong(SCANNER.nextLine());

			if (userRepository.findById(returnUserID).equals(null)) {
				throw new Exception("User ID not found!");
			}

			System.out.println("Please enter Book ID");
			Long returnBookID = Long.parseLong(SCANNER.nextLine());

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
			System.out.println("BookID " + returnBookID + " has been returned." + "\n Title: " + returnBook.getTitle());
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
	}

	@Override
	public void addBook() {
		// TODO
		BookDTO newBookDTO = new BookDTO();
		System.out.println("Please enter Book Title");
		String title = SCANNER.nextLine();
		System.out.println("Please enter Book Author");
		String author = SCANNER.nextLine();
		newBookDTO.setTitle(title);
		newBookDTO.setAuthor(author);
		newBookDTO.setIsAvailable(true);
		try {
			em.getTransaction().begin();
			bookRepository.save(newBookDTO.toEntity());

			em.flush();
			em.getTransaction().commit();
			em.clear();
			System.out.println("Book successfully added");
			displayAllBooks();
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

	}

	@Override
	public void removeBook() {
		// TODO
		System.out.println("Please enter Book ID");
		try {
			Long delBookID = Long.parseLong(SCANNER.nextLine());
			em.getTransaction().begin();
			if (loanRepository.findByBookID(delBookID) > 0) {
				System.out.println("Book is loaned, removing from loan DB");
				loanRepository.delete(loanRepository.findById(loanRepository.findByBookID(delBookID)));
			}

			bookRepository.deleteById(delBookID);

			em.flush();
			em.getTransaction().commit();
			em.clear();
			System.out.println("Book has been removed");
			displayAllBooks();
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
	}

	@Override
	public void updateBook() {
		// TODO
		System.out.println("Please enter Book ID");
		Long updBookID = Long.parseLong(SCANNER.nextLine());
		
		if (bookRepository.findById(updBookID) == null) {
			System.out.println("Book ID not found in DB");
		} else {
			if (bookRepository.findById(updBookID).getBookIsAvailable().equals(true)) {
				// Insert update of Book Title and Author
				System.out.println("Please enter new Book Title");
				String title = SCANNER.nextLine();
				System.out.println("Please enter new Book Author");
				String author = SCANNER.nextLine();

				BookDTO newBookDTO = new BookDTO();
				newBookDTO.setId(updBookID);
				newBookDTO.setTitle(title);
				newBookDTO.setAuthor(author);

				try {
					em.getTransaction().begin();
					bookRepository.update(newBookDTO.toEntity());

					em.flush();
					em.getTransaction().commit();
					em.clear();
					System.out.println("Book has been updated");
					displayAllBooks();
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

			}
		}

	}
}
