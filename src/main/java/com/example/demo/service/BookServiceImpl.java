package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Book;

public class BookServiceImpl implements BookService {
	private static List<Book> book = new ArrayList<>();
	private static Long ID_SEQ = 0L;

	public BookServiceImpl() {
		book.add(new Book(1L, "The 7 Habits of Highly Effective People", "Stephen Covey"));
		book.add(new Book(2L, "The Richest Man in Babylon", "George S. Clason"));
		book.add(new Book(3L, "Start With Why", "Simon Sinek"));
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return book;
	}

	@Override
	public Book getBookByID(long id) throws Exception {
		// TODO Auto-generated method stub
		Boolean found = false;
		Book resBook = null;
		for (Book b : book) {
			if (b.getId() == id) {
				found = true;
				resBook = b;
			}
		}

		if (found == false) {
			throw new Exception("Book ID not found in list");
		}

		return resBook;
	}

	@Override
	public Book addBook(Book newbook) throws Exception {
		// TODO Auto-generated method stub
		// Get the last record in the List of Books
		Book lastBook = null;
		if (!book.isEmpty()) {
			lastBook = book.get(book.size() - 1);
			ID_SEQ = lastBook.getId() + 1L;
		} else {
			ID_SEQ = 1L;
		}
		// Forces the computed value of index instead of accepting the user input
		newbook.setId(ID_SEQ);

		try {
			book.add(newbook); // <-- this will now work
		} catch (Exception e) {
			e.printStackTrace();
		}

		return getBookByID(newbook.getId());

	}

	@Override
	public List<Book> searchByTitle(String title) {
		// TODO Auto-generated method stub
		List<Book> resBookList = new ArrayList<>();
		for (Book b : book) {
			if (title.trim().equalsIgnoreCase(b.getTitle().trim())) {
				resBookList.add(b);
			}
		}
		return resBookList;
	}

	@Override
	public List<Book> searchByAuthor(String author) {
		// TODO Auto-generated method stub
		List<Book> resBookList = new ArrayList<>();
		for (Book b : book) {
			if (author.trim().equalsIgnoreCase(b.getAuthor().trim())) {
				resBookList.add(b);
			}
		}
		return resBookList;
	}

	@Override
	public List<Book> searchByAvailability(Boolean availability) {
		// TODO Auto-generated method stub
		List<Book> resBookList = new ArrayList<>();
		for (Book b : book) {
			if (b.getIsAvailable() == availability) {
				resBookList.add(b);
			}
		}
		return resBookList;
	}

	@Override
	public List<Book> searchByAuthorAndAvailability(String author, Boolean availability) {
		// TODO Auto-generated method stub
		List<Book> resBookList = new ArrayList<>();
		for (Book b : book) {
			if (author.trim().equalsIgnoreCase(b.getAuthor().trim()) && b.getIsAvailable() == availability) {
				resBookList.add(b);
			}
		}
		return resBookList;
	}

	@Override
	public Book deleteBook(Long id) throws Exception {
		// TODO Auto-generated method stub
		Book delBook = null;
		try {
			delBook = getBookByID(id);
			
			int idx = -1;
			for (int i = 0; i < book.size(); i++) {
				Book r = book.get(i);
				if (r == delBook) {
					idx = i;
					break;
				}
			}
			
			book.remove(idx);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return delBook;
	}

}
