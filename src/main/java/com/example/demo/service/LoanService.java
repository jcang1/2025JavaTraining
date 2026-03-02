package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DAO.Loan;
import com.example.demo.DTO.LoanDTO;
import com.example.demo.repository.LoanRepository;
@Service
public class LoanService {
	
	private final LoanRepository repo;
	
	public LoanService(LoanRepository repo) {
		this.repo = repo;
	}
	
	public LoanDTO addLoan(Loan loan) {
		return toDto(repo.save(loan));
	}
	
	public LoanDTO searchByBookId(Long bookId) {
		return toDto(repo.findByBook_Id(bookId));
	}

	public Long loanByUser(Long userId) {
		return repo.countByUser_Id(userId);
	}
	
	@Transactional
	public long deleteByBookId(Long bookId) {
		return repo.deleteByBook_Id(bookId);
	}
	
	private LoanDTO toDto(Loan l) {
		LoanDTO dto = new LoanDTO();
		dto.setId(l.getLoanID());
		dto.setBookId(l.getBook().getId());
		dto.setUserId(l.getUser().getId());
		return dto;
	}
}
