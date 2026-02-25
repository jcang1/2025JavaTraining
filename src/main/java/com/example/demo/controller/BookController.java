package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.service.BookServiceImpl;

@RestController
@RequestMapping("/api/books")
public class BookController {

	public BookController() {

	}

	private BookServiceImpl bookServiceImpl = new BookServiceImpl();

	@SuppressWarnings("rawtypes")
	@GetMapping
	public List getAll() {
		return bookServiceImpl.getAllBooks();
	}

	// The variable in @GetMapping and @PathVariable should be the same
	@GetMapping("/{id}")
	public Book getOne(@PathVariable Long id) throws Exception {
		Book resBook = bookServiceImpl.getBookByID(id);

		return resBook;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book create(@RequestBody Book newbook) throws Exception {
		Book resBook = bookServiceImpl.addBook(newbook);
		return resBook;
	}
	
	@GetMapping("/searchbyTitle")
	public List<Book> searchByTitle(@RequestParam String title) {
		return bookServiceImpl.searchByTitle(title);		
	}
	
	@GetMapping("/searchbyAuthor")
	public List<Book> searchByAuthor(@RequestParam String author) {
		return bookServiceImpl.searchByAuthor(author);		
	}
	
	@GetMapping("/searchByAvailability")
	public List<Book> searchByAvailability(@RequestParam Boolean availability) {
		return bookServiceImpl.searchByAvailability(availability);	
	}
	
	@GetMapping("/searchByAuthorAndAvailability")
	public List<Book> searchByAuthorAndAvailability(
			@RequestParam String author,
			@RequestParam Boolean availability) {
		return bookServiceImpl.searchByAuthorAndAvailability(author, availability);
	}
	
	
}
