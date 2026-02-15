/**
 * 
 */
package com.bpi.m6groupproject.repository;

import java.util.List;
import com.bpi.m6groupproject.model.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * 
 */
public class BookRepository implements Repository<Book, Long> {

	private final EntityManager em;

	public BookRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Book save(Book newBook) {
		// TODO
		
		if (newBook.getBookID() == null) {
			em.persist(newBook);
		} else {
			newBook = em.merge(newBook);
		}
		
		return newBook;
	}

	@Override
	public void delete(Book delBook) {
		// TODO
		
		em.remove(em.contains(delBook) ? delBook : em.merge(delBook));
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO
		Book book = findById(id);

		if (book != null) {
			delete(book);
		} else {
			System.out.println("Book ID not found!");
		}
	}

	@Override
	public Book findById(Long id) {
		// TODO
		return em.find(Book.class, id);
	}

	@Override
	public List<Book> findAll() {
		// TODO
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Book> cq = cb.createQuery(Book.class);
		Root<Book> root = cq.from(Book.class);
		cq.select(root).orderBy(cb.asc(root.get("id")));
		TypedQuery<Book> query = em.createQuery(cq);
		return query.getResultList();
	}

	public void update(Book bookEntity) {
		// TODO
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<Book> update = cb.createCriteriaUpdate(Book.class);
		Root<Book> root = update.from(Book.class);

		update.set(root.get("title"), bookEntity.getTitle());
		update.set(root.get("author"), bookEntity.getAuthor());
		update.where(cb.equal(root.get("id"), bookEntity.getBookID()));
		
		em.createQuery(update).executeUpdate();

	}


    public List<Book> findAllAvailable() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);

        Predicate available = cb.isTrue(root.get("isAvailable"));
        cq.select(root).where(available).orderBy(cb.asc(root.get("id")));

        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }
    
    public List<Book> findAllNotAvailable() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);

        Predicate notAvailable = cb.isFalse(root.get("isAvailable"));
        cq.select(root).where(notAvailable).orderBy(cb.asc(root.get("id")));

        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }
	
	

}
