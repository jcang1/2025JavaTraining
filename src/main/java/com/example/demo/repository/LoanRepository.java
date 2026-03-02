package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.DAO.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
	Loan findByBook_Id(Long bookId);
	Long countByUser_Id(Long userId);
	Long deleteByBook_Id(Long bookId);
}
