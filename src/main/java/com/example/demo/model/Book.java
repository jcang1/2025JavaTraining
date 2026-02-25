package com.example.demo.model;

import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.BookDTOMasked;

public class Book {
	
	private Long id;
	private String title;
	private String author;
	private Boolean isAvailable;
	
	public Book() {
		
	}
	
	public Book (Long id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isAvailable = true;
	}

	public Long getId() { return id; }
	public String getTitle() { return title; }
	public String getAuthor() { return author; }
	public Boolean getIsAvailable() { return isAvailable; }
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
		
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public BookDTO toDTO() {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(id);
		bookDTO.setTitle(title);
		bookDTO.setAuthor(author);
		bookDTO.setIsAvailable(isAvailable);
		return bookDTO;
	}
	
	public BookDTOMasked toDTOMasked() {
		BookDTOMasked bookDTOMasked = new BookDTOMasked();
		bookDTOMasked.setTitle(title);
		bookDTOMasked.setAuthor(author);
		return bookDTOMasked;
	}
	
}
