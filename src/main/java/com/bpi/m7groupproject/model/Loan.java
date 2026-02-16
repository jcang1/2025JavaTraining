package com.bpi.m7groupproject.model;

//import com.bpi.m7groupproject.dto.LoanDTO;
//import com.bpi.m7groupproject.repository.*;

import jakarta.persistence.Entity;
//import jakarta.persistence.EntityManager;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//import jakarta.persistence.Transient;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Many loans can refer to the same book over time
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id")
    private Book book;    
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    
//    @Transient
//    private final transient EntityManager em;
    
//    @Transient
//    private final BookRepository bookRepository;
//    @Transient
//	private final UserRepository userRepository;
//    @Transient
//	private final LoanRepository loanRepository;
	
//	public Loan(EntityManager em) {
//		this.em = em;
//		this.bookRepository = new BookRepository(em);
//		this.userRepository = new UserRepository(em);
//		this.loanRepository = new LoanRepository(em);
//	}
	
	public Long getLoanID() {
		return this.id;
	}
    
    public User getUser() {
    	return this.user;
    }
    
    public Book getBook() {
    	return this.book;
    }
    
	public void setLoan(User user, Book book) {
		this.user = user;
		this.book = book;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
//	public LoanDTO toDTO() {
//		
//		LoanDTO loanDTO = new LoanDTO(em, this.id, this.book.getBookID(), //this.user.getId());
//		
//		return loanDTO;
//	}
}
