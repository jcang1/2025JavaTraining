/*
 * Controller. This contains the URL available for testing via API
 */

package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.DTO.ApiResponse;
import com.example.demo.DTO.BookDTO;
import com.example.demo.services.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	// GET /api/books
	@GetMapping("/listBook")
	public ResponseEntity<List<BookDTO>> getBooks() {
		logger.info("accessing /api/books/listBook");
		return ResponseEntity.ok(bookService.getAllBooks());
	}

	@PostMapping("/createBook")
	public ResponseEntity<ApiResponse<BookDTO>> addBook(@RequestParam String title, @RequestParam String author,
			@RequestParam LocalDate publishDate) {
		logger.info("accessing /api/books/createBook");
		return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(title, author, publishDate));
	}

	@PostMapping("/readBook")
	public ResponseEntity<ApiResponse<BookDTO>> borrowBook(@RequestParam Long bookId, @RequestParam Long userId) {
		logger.info("accessing /api/books/readBook");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookService.readBook(bookId, userId));
	}

	@PostMapping("/unReadBook")
	public ResponseEntity<ApiResponse<BookDTO>> returnBook(@RequestParam Long bookId) {
		logger.info("accessing /api/books/unReadBook");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookService.unReadBook(bookId));
	}

	@PatchMapping("/updateBook")
	public ResponseEntity<ApiResponse<BookDTO>> updateBook(@RequestParam Long id, @RequestParam String title,
			@RequestParam String author, @RequestParam LocalDate publishDate) {
		logger.info("accessing /api/books/updateBook");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookService.updateBook(id, title, author, publishDate));
	}

	@DeleteMapping("/delBook")
	public ResponseEntity<ApiResponse<BookDTO>> delBook(@RequestParam Long id) {
		logger.info("accessing /api/books/delBook");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookService.valDelBook(id));
	}

}