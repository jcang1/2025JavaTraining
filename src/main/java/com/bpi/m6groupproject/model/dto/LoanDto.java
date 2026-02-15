package com.bpi.m6groupproject.model.dto;

import com.bpi.m6groupproject.model.Book;
import com.bpi.m6groupproject.model.Loan;
import com.bpi.m6groupproject.model.User;

public class LoanDto {
    private Long id;
    private Long bookId;
    private Long userId;

    public LoanDto() {}

    public LoanDto(Long id, Long bookId, Long userId) {
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
    
    public void LoanDTO(Loan loanEntity) {
    	this.id = loanEntity.getLoanID();
    	this.bookId = loanEntity.getBook().getBookID();
    	this.userId = loanEntity.getUser().getId();
    }
    
    public Loan toEntity() {
    	Loan loanToEntity = new Loan();
    	//TODO cannot complete this part yet since Loan contructor is setLoan(User user, Book book)
    	return loanToEntity;
    }
}