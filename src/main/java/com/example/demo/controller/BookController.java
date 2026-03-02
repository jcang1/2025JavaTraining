package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.BookDTOMasked;
import com.example.demo.DTO.LoanDTO;
import com.example.demo.service.LibaryService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final LibaryService libaryService;

    public BookController(LibaryService libaryService) {
        this.libaryService = libaryService;
    }

    // GET /api/books
    @GetMapping
    public ResponseEntity<List<BookDTOMasked>> getBooks(@RequestParam(required = false) String title,
                                                        @RequestParam(required = false) String author,
                                                        @RequestParam(required = false) Boolean isAvailable) {
        return ResponseEntity.ok(libaryService.getBooks(title, author, isAvailable));
    }

    @PostMapping("/addBook")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDTO> addBook(@RequestParam String title,
                                           @RequestParam String author) {
        return ResponseEntity.ok(libaryService.addBook(title, author));
    }

    @DeleteMapping("/delBook")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> delBook(@RequestParam String title,
                                          @RequestParam String author) {
        long count = libaryService.deleteBookByTitleAndAuthor(title, author);
        if (count == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("No book found with given title and author.");
        }
        return ResponseEntity.ok("Deleted " + count + " record(s).");
    }

    @PatchMapping("/updateBook")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<BookDTO> updateBook(@RequestParam Long id,
                                              @RequestParam String title,
                                              @RequestParam String author) {
        BookDTO book = libaryService.updateBook(id, title, author);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/borrowBook")
    public ResponseEntity<LoanDTO> borrowBook(@RequestParam Long bookId,
                                              @RequestParam Long userId) {
        LoanDTO loan = libaryService.borrowBook(bookId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(loan);
    }

    @PostMapping("/returnBook")
    public ResponseEntity<Long> returnBook(@RequestParam Long bookId,
                                           @RequestParam Long userId) {
        long deleted = libaryService.returnBook(bookId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(deleted);
    }
}