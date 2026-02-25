package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;

@RestController
@RequestMapping("/api/books")
public class BookController {

	public BookController() {

	}

	private static List<Book> book = new ArrayList<>();

	public void initialBooks() {
		book.add(new Book(1L, "The 7 Habits of Highly Effective People", "Stephen Covey"));
		book.add(new Book(2L, "The Richest Man in Babylon", "George S. Clason"));
		book.add(new Book(3L, "Start With Why", "Simon Sinek"));
		// book.add(new Book(4L, "The Laws of Human Nature", "Robert Greene"));
		// book.add(new Book(5L, "Unreasonable Hospitality", "Will Guidara"));
	}

	@GetMapping
	public List getAll() {
		return List.of(book);
	}
	
	//The variable in @GetMapping and @PathVariable should be the same
	@GetMapping("/{id}")
	public Book getOne(@PathVariable Long id) throws Exception {
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

}
