/**
 * 
 */
package com.bpi.m7groupproject.repository;

import java.util.List;

import com.bpi.m7groupproject.dto.*;
import com.bpi.m7groupproject.model.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * 
 */
public class LoanRepository implements Repository<Loan, Long> {

	private final EntityManager em;

	public LoanRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Loan save(Loan newloan) {
		// TODO Auto-generated method stub
		
		if (newloan.getLoanID() == null) {
			em.persist(newloan);
		} else {
			newloan = em.merge(newloan);
		}
		
		return newloan;
	}

	@Override
	public void delete(Loan delLoan) {
		// TODO Auto-generated method stub
		
		em.remove(em.contains(delLoan) ? delLoan : em.merge(delLoan));
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		Loan loan = findById(id);

		if (loan != null) {
			delete(loan);
		} else {
			System.out.println("Loan ID not found!");
		}
	}

	@Override
	public Loan findById(Long id) {
		// TODO Auto-generated method stub
		return em.find(Loan.class, id);
	}

	@Override
	public List<Loan> findAll() {
		// TODO Auto-generated method stub
		// NOT needed
		return null;
	}

	public Long countLoanedByUser(Long userID) {
		Long count = 0L;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Loan> root = cq.from(Loan.class);

		cq.select(cb.count(root));
		cq.where(cb.equal(root.get("user").get("id"), userID));

		count = em.createQuery(cq).getSingleResult();

		return count;

	}

	public Long findByUserIDAndBookID(Long userID, Long bookID) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Loan> root = cq.from(Loan.class);

		cq.select(root.get("id")).where(cb.equal(root.get("user").get("id"), userID),
				cb.equal(root.get("book").get("id"), bookID));

		try {
			return em.createQuery(cq).getSingleResult(); // safe if unique constraint exists
		} catch (NoResultException e) {
			return null;
		}
	}

	public Long findByBookID(Long bookID) {
		// This returns the loan ID
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Loan> root = cq.from(Loan.class);

		cq.select(root.get("id")).where(cb.equal(root.get("book").get("id"), bookID));

		try {
			return em.createQuery(cq).getSingleResult(); // safe if unique constraint exists
		} catch (NoResultException e) {
			return null;
		}
	}

}
