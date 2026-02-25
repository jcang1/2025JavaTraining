package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Book;

public interface BookService {
	
	List getAllBooks();

	Book getBookByID(long id) throws Exception;
	
	Book addBook(Book book) throws Exception;
	
	List<Book> searchByTitle(String title);
	
	List<Book> searchByAuthor(String author);
	
	List<Book> searchByAvailability(Boolean availability);
	
	List<Book> searchByAuthorAndAvailability(String author, Boolean availability);
}
