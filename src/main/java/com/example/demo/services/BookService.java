/*
 * Contains services available for books
 * These are invoked using class BookController
 */

package com.example.demo.services;

import com.example.demo.DAO.Book;
import com.example.demo.DAO.User;
import com.example.demo.DTO.ApiResponse;
import com.example.demo.DTO.BookDTO;
import com.example.demo.mapper.UserBookMapper;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final UserBookMapper mapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(UserBookMapper mapper,
                       BookRepository bookRepository,
                       UserRepository userRepository) {
        this.mapper = mapper;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }


	public List<BookDTO> getAllBooks() {
		return mapper.toBookDTOList(bookRepository.
				findAll()
				);
	}

	public BookDTO searchById(Long id) {
		return mapper.toBookDTO(bookRepository.
				findById(id).
				orElse(null)
				);
	}

	public List<BookDTO> searchByTitle(String title) {
		return mapper.toBookDTOList(bookRepository.
				findByTitleContainingIgnoreCase(title)
				);
	}

	public List<BookDTO> searchByAuthor(String author) {
		return mapper.toBookDTOList(bookRepository.
				findByAuthorIgnoreCase(author)
				);
	}

	public List<BookDTO> searchByReadFlag(Boolean readFlag) {
		return mapper.toBookDTOList(bookRepository.
				findByReadFlag(readFlag)
				);
	}

	public List<BookDTO> searchByAuthorAndReadFlag(String author, Boolean readFlag) {
		return mapper.toBookDTOList(bookRepository.
				findByAuthorIgnoreCaseAndReadFlag(author, readFlag)
				);
	}

	public List<BookDTO> searchByTitleAuthorAndReadFlag(String title, String author, Boolean readFlag) {
		return mapper.toBookDTOList(bookRepository.
				findByTitleContainingIgnoreCaseAndAuthorIgnoreCaseAndReadFlag(title, author, readFlag)
				);		
	}
	
	public List<BookDTO> searchByTitleAuthor(String title, String author) {
		return mapper.toBookDTOList(bookRepository.
				findByTitleIgnoreCaseAndAuthorIgnoreCase(title, author)
				);
	}

	public ApiResponse<BookDTO> addBook(String title, String author, LocalDate publishedDate) {
		logger.info("called method addBook");
		Book newBook = new Book();
		newBook.setTitle(title);
		newBook.setAuthor(author);
		newBook.setPublishedDate(publishedDate);
		newBook.setLibraryRecordCreatedDate(LocalDate.now());
		newBook.setUser(null);
		
		Book savedBook = bookRepository.save(newBook);
		logger.info("Book created successfully!");
		logger.info("Book ID: " + savedBook.getId());
		logger.info("Book Title: " + title);
		logger.info("Book Author: " + author);
		logger.info("Book publishedDate: " + publishedDate);
		
		return new ApiResponse<>("Book created successfully!", mapper.toBookDTO(newBook));
	}

	public ApiResponse<BookDTO> updateBook(Long id, String title, String author, LocalDate publishedDate) {
		// TODO Auto-generated method stub
		logger.info("called method updateBook");
		Book updateBook = bookRepository.findById(id).get();
		
		BookDTO bookDTO = null;
		String message = null;
		
		if (updateBook == null) {
			message = "Book ID:" + id + "does not exists";
			logger.warn(message);
		} else {
			if (updateBook.isReadFlag() == true) {
				message = "Cannot update book. Currently tag as being read.";	
				logger.warn(message);
			} else {
				if (updateBook.isReadFlag() == false) {
					logger.info("Book id" + id);
					logger.info("Prev Book Title: " + updateBook.getTitle());
					logger.info("Prev Book Author: " + updateBook.getAuthor());
					logger.info("Prev Book Published Date: " + updateBook.getPublishedDate());
					
					updateBook.setTitle(title);
					updateBook.setAuthor(author);
					updateBook.setPublishedDate(publishedDate);
					
					logger.info("updating book details");
					
					bookRepository.save(updateBook);
					bookDTO =  mapper.toBookDTO(updateBook);
					message = "Successful update!";
					
					logger.info(message);
					logger.info("New Book Title: " + title);
					logger.info("New Book Author: " + author);
					logger.info("New Book publishedDate: " + publishedDate);
				}
			}
		}		
		return new ApiResponse<>(message, bookDTO);
	}

	@Transactional
	private BookDTO deleteBook(Long id) {
		BookDTO deleteBookDTO = searchById(id);
		bookRepository.findById(id);
		bookRepository.deleteById(id);
		return deleteBookDTO;
	}
	
	public ApiResponse<BookDTO> valDelBook(Long id){
		logger.info("called method valDelBook");
		Book valDelBook = bookRepository.findById(id).orElseThrow(null);
		
		BookDTO bookDTO = null;
		String message = null;
		
		if (valDelBook == null) {
			message = "book ID " + id + "does not exists!";
			logger.warn(message);
		} else {
			LocalDate bookCrea = valDelBook.getLibraryRecordCreatedDate();
			LocalDate today = LocalDate.now();
			
			boolean validDel = 
					bookCrea.isBefore(today.minusWeeks(1)) &&
					bookCrea.isAfter(today.minusYears(1));
			
			if (validDel) {
				bookDTO = deleteBook(id);
				message = "Book Deleted";
				logger.info("Book id" + id);
				logger.info(message);
				
			} else {
				bookDTO = searchById(id);
				message = "Cannot delete book. "
						+ "Outside the allowed created range. "
						+ "Should be more than 1 week and less then 1 year";
				logger.warn(message);
			}
		}
		return new ApiResponse<>(message, bookDTO);
	}
	
	public ApiResponse<BookDTO> readBook(Long bookId, Long userId) {
		// TODO Auto-generated method stub
		logger.info("called method readBook");
		logger.info("Processing Book ID: " + bookId);
		Book readBook = bookRepository.findById(bookId).get();
		
		BookDTO bookDTO = null;
		String message = null;
		
		if (readBook == null) {
			message = "Book ID:" + bookId + "does not exists";
			logger.warn(message);
		} else {
			if (readBook.isReadFlag() == true) {
				message = "Cannot proceed. Currently tag as being read.";	
				logger.warn(message);
			} else {
				if (readBook.isReadFlag() == false) {
					logger.info("Book id" + bookId);

					User reader = 
							userRepository.findById(userId)
					        .orElseThrow(() -> new RuntimeException("User not found"));
					
					readBook.setReadFlag(true);
					readBook.setUser(reader);
					
					logger.info("updating book details");
					
					bookRepository.save(readBook);
					
					logger.info("book has been tagged as read");
					
					bookDTO =  mapper.toBookDTO(readBook);
					message = "Successful update!";
					
					logger.info(message);
				}
			}
		}		
		return new ApiResponse<>(message, bookDTO);
	}
	
	public ApiResponse<BookDTO> unReadBook(Long bookId) {
		// TODO Auto-generated method stub
		logger.info("called method unReadBook");
		logger.info("Processing Book ID: " + bookId);
		Book unReadBook = bookRepository.findById(bookId).get();
		
		BookDTO bookDTO = null;
		String message = null;
		
		if (unReadBook == null) {
			message = "Book ID:" + bookId + "does not exists";
			logger.warn(message);
		} else {
			if (unReadBook.isReadFlag() == false) {				
				message = "Cannot proceed. Currently tag as being unread.";	
				logger.warn(message);
			} else {
				if (unReadBook.isReadFlag() == true) {
					logger.info("Book id" + bookId);

					unReadBook.setReadFlag(false);
					unReadBook.setUser(null);
					
					logger.info("updating book details");
					
					bookRepository.save(unReadBook);
					
					logger.info("book has been tagged as unread");
					
					bookDTO =  mapper.toBookDTO(unReadBook);
					message = "Successful update!";
					
					logger.info(message);
				}
			}
		}		
		return new ApiResponse<>(message, bookDTO);
	}
	
}
