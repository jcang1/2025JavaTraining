package com.bpi.m6groupproject.service;

public interface BookService {
	
	void displayAllBooks();
	
	void displayAllAvailableBooks();
	
	void displayAllBorrowedBooks();
	
	void borrowBook();
	
	void returnBook();
	
	void addBook();
	
	void removeBook();
	
	void updateBook();
}
