package com.bpi.m7groupproject.dto;

import jakarta.persistence.EntityManager;
import com.bpi.m7groupproject.model.Loan;
import com.bpi.m7groupproject.model.User;
import com.bpi.m7groupproject.model.Book;

public class LoanDTO {
    private Long id;
    private Long bookId;
    private Long userId;

    // REQUIRED by Jackson
    public LoanDTO() {}

    public LoanDTO(Long id, Long bookId, Long userId) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Loan toEntity(EntityManager em) {
        if (em == null) {
            throw new IllegalArgumentException("EntityManager must not be null");
        }
        if (userId == null || bookId == null) {
            throw new IllegalStateException("Both userId and bookId must be provided");
        }

        // Create lightweight references; no DB query until accessed if provider supports proxies.
        User userRef = em.getReference(User.class, userId);
        Book bookRef = em.getReference(Book.class, bookId);

        Loan loan;

        // ---- Choose ONE of the two branches depending on your Loan API ----
        // (1) If Loan has a constructor Loan(User user, Book book):
        try {
            loan = Loan.class.getDeclaredConstructor(User.class, Book.class)
                             .newInstance(userRef, bookRef);
        } catch (ReflectiveOperationException e) {
            // (2) Otherwise, fall back to no-args + setters:
            loan = new Loan();
            loan.setUser(userRef);
            loan.setBook(bookRef);
        }

        if (id != null) {
            // Replace setLoanID with your actual ID setter if different
            loan.setId(id);
        }
        return loan;
    }
}
