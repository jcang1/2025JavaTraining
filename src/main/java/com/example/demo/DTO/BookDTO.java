package com.example.demo.DTO;

import com.example.demo.model.Book;

public class BookDTO {
	
	private Long id;
	private String title;
	private String author;
	private Boolean isAvailable;
	
	public BookDTO() {
		
	}
	
	public BookDTO (Long id, String title, String author) {
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
	
	public Book toEntity() {
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setAuthor(author);
		book.setIsAvailable(isAvailable);
		return book;
	}
		
}
