package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.BookDTOMasked;
import com.example.demo.model.Book;
import com.example.demo.service.BookServiceImpl;

@RestController
@RequestMapping("/api/books")
public class BookController {

	public BookController() {

	}

	private BookServiceImpl bookServiceImpl = new BookServiceImpl();
	

	@GetMapping
	@ResponseBody
	public List<BookDTOMasked> getAll() {
		return bookServiceImpl.getAllBooks().stream().map(Book::toDTOMasked).toList();
	}

	// The variable in @GetMapping and @PathVariable should be the same
	@GetMapping("/{id}")
	public BookDTOMasked getOne(@PathVariable Long id) throws Exception {
		BookDTOMasked resBook = null;
		if (id != null) {
			resBook = bookServiceImpl.getBookByID(id).toDTOMasked();
		}
		return resBook;
	}

//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public BookDTO create(@RequestBody BookDTO newBook) throws Exception {
//		BookDTO resBook = null;
//		if (newBook != null) {
//			resBook = bookServiceImpl.addBook(newBook.toEntity()).toDTO();
//		}
//		return resBook;
//	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public List<BookDTOMasked> createBulk(@RequestBody List<BookDTO> newBook) throws Exception {
		List<BookDTOMasked> resBookList = new ArrayList<>();
		if (newBook != null) {
			for (BookDTO nb : newBook) {
				
				resBookList.add(bookServiceImpl.addBook(nb.toEntity()).toDTOMasked());
			}
			//bookServiceImpl.addBook(newBook.toEntity()).toDTO();
		}
		return resBookList;
	}

	@GetMapping("/searchbyTitle")
	public List<BookDTO> searchByTitle(@RequestParam String title) {
		if (title.trim().isBlank() || title.trim().isEmpty()) {
			return null;
		} else {
			return bookServiceImpl.searchByTitle(title).stream().map(Book::toDTO).toList();
		}
	}

	@GetMapping("/searchbyAuthor")
	public List<BookDTO> searchByAuthor(@RequestParam String author) {
		if (author.trim().isBlank() || author.trim().isEmpty()) {
			return null;
		} else {
			return bookServiceImpl.searchByAuthor(author).stream().map(Book::toDTO).toList();
		}
	}

	@GetMapping("/searchByAvailability")
	public List<BookDTO> searchByAvailability(@RequestParam Boolean availability) {
		if (availability == null) {
			return null;
		} else {
			return bookServiceImpl.searchByAvailability(availability).stream().map(Book::toDTO).toList();
		}
	}

	@GetMapping("/searchByAuthorAndAvailability")
	public List<BookDTO> searchByAuthorAndAvailability(@RequestParam String author,
			@RequestParam Boolean availability) {
		if (author.trim().isBlank() || author.trim().isEmpty() || availability == null) {
			return null;
		} else {
			return bookServiceImpl.searchByAuthorAndAvailability(author, availability).stream().map(Book::toDTO).toList();
		}
		
	}
	
	@DeleteMapping("/deleteBook/{id}")	
	public String deleteBook(@PathVariable Long id) throws Exception {
		
		String response =null;
		try {
		String delTitle = bookServiceImpl.getBookByID(id).getTitle();
		String delAuthor = bookServiceImpl.getBookByID(id).getAuthor();
		
		bookServiceImpl.deleteBook(id);
		
		response = String.format(
				"Deleted Book ID: %d, Book Title: %s, Book Author: %s",
	            id, delTitle, delAuthor
	        );

		} catch(Exception e) {
			response = String.format("Error encountered deleting Book ID %d", id);
			e.printStackTrace();
		}
	    return response;
	}


}
