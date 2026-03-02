package com.example.demo.service;

import com.example.demo.DAO.Book;
import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.BookDTOMasked;
import com.example.demo.repository.BookRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

	private final BookRepository repo;

	public BookService(BookRepository repo) {
		this.repo = repo;
	}

	public List<BookDTO> getAllBooks() {
		return toDtoList(repo.findAll());
	}

	public BookDTO searchById(Long id) {
		return toDto(repo.findById(id).orElse(null));
	}

	public List<BookDTO> searchByTitle(String title) {
		return toDtoList(repo.findByTitleContainingIgnoreCase(title));
	}

	public List<BookDTO> searchByAuthor(String author) {
		return toDtoList(repo.findByAuthorIgnoreCase(author));
	}

	public List<BookDTO> searchByAvailability(Boolean isAvailable) {
		return toDtoList(repo.findByAvailable(isAvailable));
	}

	public List<BookDTO> searchByAuthorAndAvailability(String author, Boolean isAvailable) {
		return toDtoList(repo.findByAuthorIgnoreCaseAndAvailable(author, isAvailable));
	}

	public List<BookDTO> searchByTitleAuthorAndAvailability(String title, String author, Boolean isAvailable) {
		return toDtoList(
				repo.findByTitleContainingIgnoreCaseAndAuthorIgnoreCaseAndAvailable(title, author, isAvailable));
	}
	
	public List<BookDTO> searchByTitleAuthor(String title, String author) {
		return toDtoList(
				repo.findByTitleIgnoreCaseAndAuthorIgnoreCase(title, author));
	}

	public BookDTO addBook(String title, String author) {
		Book newBook = new Book(title, author, Boolean.TRUE);
		repo.save(newBook);
		return toDto(newBook);
	}

	public BookDTO updateBook(Long id, String title, String author) {
		// TODO Auto-generated method stub
		Book updateBook = repo.findById(id).get();

		if (updateBook.getAvailable() == true) {
			updateBook.setTitle(title);
			updateBook.setAuthor(author);
			repo.save(updateBook);
			return toDto(updateBook);
		} else {
			return null;
		}
	}
	
	
	public BookDTO updateBookLoan(Long id, Boolean isAvailable) {
		// TODO Auto-generated method stub
		Book updateBook = repo.findById(id).get();
		updateBook.setAvailable(isAvailable); //True if return, False if borrow
		repo.save(updateBook);
		return toDto(updateBook);
	}

	@Transactional
	public long deleteByTitleAndAuthor(String title, String author) {
		return repo.deleteByTitleIgnoreCaseAndAuthorIgnoreCase(title, author);
	}

	private BookDTO toDto(Book b) {
		BookDTO dto = new BookDTO();
		dto.setId(b.getId());
		dto.setTitle(b.getTitle());
		dto.setAuthor(b.getAuthor());
		dto.setIsAvailable(b.getAvailable());
		return dto;
	}
	
	private BookDTOMasked toDtoM(BookDTO b) {
		BookDTOMasked dtoM = new BookDTOMasked();
		dtoM.setTitle(b.getTitle());
		dtoM.setAuthor(b.getAuthor());
		dtoM.setIsAvailable(b.getIsAvailable());
		return dtoM;
	}
	
	public Book toEntity(BookDTO b) {
		Book entity = new Book();
		entity.setId(b.getId());
		entity.setTitle(b.getTitle());
		entity.setAuthor(b.getAuthor());
		entity.setAvailable(b.getIsAvailable());
		return entity;
	}
	
	private List<BookDTO> toDtoList(List<Book> b) {
		return b.stream().sorted(Comparator.comparing(Book::getId)).map(this::toDto).collect(Collectors.toList());
	}	

}
