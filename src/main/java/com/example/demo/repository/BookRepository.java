/*
 * Contains all the queries to access database specific for table books
 */

package com.example.demo.repository;

import com.example.demo.DAO.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorIgnoreCase(String author);
    List<Book> findByReadFlag(Boolean readFlag);
    List<Book> findByAuthorIgnoreCaseAndReadFlag(String author, Boolean readFlag);
    List<Book> findByTitleContainingIgnoreCaseAndAuthorIgnoreCaseAndReadFlag(String title, String author, Boolean readFlag);
    List<Book> findByTitleIgnoreCaseAndAuthorIgnoreCase(String title, String author);
    long deleteByTitleIgnoreCaseAndAuthorIgnoreCase(String title, String author);
    

    Book findFirstByTitleIgnoreCaseAndAuthorIgnoreCaseAndReadFlagIsFalseOrderByIdAsc(String title, String author);
    Book findFirstByTitleContainingIgnoreCaseAndReadFlagIsFalseOrderByIdAsc(String title);
    Optional<Book> findById(Long id);
    
    /* Spring Data JPA automatically provides:
     * save(book);
     * findById(id);
     * findAll();
     * deleteById(id);
     * delete(book);
     */
    
}