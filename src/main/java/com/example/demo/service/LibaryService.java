package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DAO.Book;
import com.example.demo.DAO.Loan;
import com.example.demo.DAO.User;
import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.BookDTOMasked;
import com.example.demo.DTO.LoanDTO;
import com.example.demo.DTO.UserDTO;

/**
 * Facade over BookService, UserService, and LoanService.
 * BookController should call only this class.
 */
@Service
public class LibaryService {

    private final BookService bookService;
    private final UserService userService;
    private final LoanService loanService;

    public LibaryService(BookService bookService, UserService userService, LoanService loanService) {
        this.bookService = bookService;
        this.userService = userService;
        this.loanService = loanService;
    }

    /* -------------------------
     * Read operations (facade)
     * ------------------------- */

    @Transactional(readOnly = true)
    public List<BookDTOMasked> getBooks(String title, String author, Boolean isAvailable) {
        // Normalize inputs
        String t = (title != null && !title.isBlank()) ? title.trim() : null;
        String a = (author != null && !author.isBlank()) ? author.trim() : null;
        Boolean avail = isAvailable; // may be null

        List<BookDTO> books;

        // Keep the same branching logic as your current controller
        if (t == null && a == null && avail == null) {
            books = bookService.getAllBooks();
        } else if (t != null && a == null && avail == null) {
            books = bookService.searchByTitle(t);
        } else if (t == null && a != null && avail == null) {
            books = bookService.searchByAuthor(a);
        } else if (t == null && a == null && avail != null) {
            books = bookService.searchByAvailability(avail);
        } else if (t == null && a != null && avail != null) {
            books = bookService.searchByAuthorAndAvailability(a, avail);
        } else if (t != null && a != null && avail != null) {
            books = bookService.searchByTitleAuthorAndAvailability(t, a, avail);
        } else {
            // For combinations your controller didn’t implement,
            // pick a sensible default or add new BookService methods later.
            // For now, return empty or all; here we choose empty:
            books = List.of();
        }

        return books.stream().map(this::toMaskedDto).collect(Collectors.toList());
    }

    /* --------------------------
     * Book CRUD (pass-through)
     * -------------------------- */

    public BookDTO addBook(String title, String author) {
        return bookService.addBook(title, author);
    }

    public long deleteBookByTitleAndAuthor(String title, String author) {
    	List<BookDTO> b = bookService.searchByTitleAuthor(title, author);
    	List<Book> bEntity = b.stream().map(this::toEntity).collect(Collectors.toList());
    	for (Book b1 : bEntity) {
    		loanService.deleteByBookId(b1.getId());
    	}
        return bookService.deleteByTitleAndAuthor(title, author);
    }

    public BookDTO updateBook(Long id, String title, String author) {
        BookDTO existing = bookService.searchById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Book " + id + " not found");
        }
        return bookService.updateBook(id, title, author);
    }

    /* --------------------------------
     * Borrow / Return (transactional)
     * -------------------------------- */

    @Transactional(rollbackFor = Exception.class)
    public LoanDTO borrowBook(Long bookId, Long userId) {
        // 1) Load DTOs
        BookDTO bookDto = bookService.searchById(bookId);
        if (bookDto == null) {
            throw new IllegalArgumentException("Book " + bookId + " not found");
        }
        UserDTO userDto = userService.searchById(userId);
        if (userDto == null) {
            throw new IllegalArgumentException("User " + userId + " not found");
        }

        // 2) Convert to entities
        Book b = bookService.toEntity(bookDto);
        User u = userService.toEntity(userDto);

        // 3) Business rules
        if (loanService.loanByUser(userId) >= 5) {
            throw new IllegalStateException("User has reached the loan limit");
        }
        if (Boolean.FALSE.equals(b.getAvailable())) {
            throw new IllegalStateException("Book is already loaned");
        }

        // 4) Create the loan and mark book unavailable
        bookService.updateBookLoan(bookId, false);
        b.setAvailable(false);

        Loan l = new Loan();
        l.setLoan(u, b);

        // 5) Persist via existing service
        return loanService.addLoan(l);
    }

    @Transactional(rollbackFor = Exception.class)
    public long returnBook(Long bookId, Long userId) {
        // 1) Validate book exists
        BookDTO bookDto = bookService.searchById(bookId);
        if (bookDto == null) {
            throw new IllegalArgumentException("Book " + bookId + " not found");
        }

        // 2) Delete loan(s) and mark book available
        long deleted = loanService.deleteByBookId(bookId);
        bookService.updateBookLoan(bookId, true);

        Book b = bookService.toEntity(bookDto);
        b.setAvailable(true);

        return deleted;
    }

    /* --------------------------
     * Mapping helper
     * -------------------------- */
    private BookDTOMasked toMaskedDto(BookDTO b) {
        BookDTOMasked dtoM = new BookDTOMasked();
        dtoM.setTitle(b.getTitle());
        dtoM.setAuthor(b.getAuthor());
        dtoM.setIsAvailable(b.getIsAvailable());
        return dtoM;
    }
    
    private Book toEntity(BookDTO b) {
        Book entity = new Book();
        entity.setId(b.getId());
        entity.setTitle(b.getTitle());
        entity.setAuthor(b.getAuthor());
        return entity;
    }

}