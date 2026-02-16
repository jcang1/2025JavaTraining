package com.bpi.m7groupproject.dto;

import com.bpi.m7groupproject.model.Book;

public class BookDTO {
	private Long id;
	private String title;
	private String author;
	private Boolean isAvailable;

	public BookDTO() {
	}

	public BookDTO(Long id, String title, String author, Boolean isAvailable) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isAvailable = isAvailable;
	}
	
	public BookDTO(String title, String author) {
		this.id = null;
		this.title = title;
		this.author = author;
		this.isAvailable = true;
	}

	// Convenience static factory
	public static BookDTO of(Long id, String title, String author, Boolean isAvailable) {
		return new BookDTO(id, title, author, isAvailable);
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public BookDTO(Book bookEntity) {
		this.setId(bookEntity.getBookID());
		this.setTitle(bookEntity.getTitle());
		this.setAuthor(bookEntity.getAuthor());
		this.setIsAvailable(bookEntity.getBookIsAvailable());
	}
	
	public Book toEntity() {
		Book bookToEntity = new Book();
		bookToEntity.setBookId(this.id);
		bookToEntity.setBook(this.title, this.author);
		bookToEntity.setBookIsAvailable(this.isAvailable);
		
		return bookToEntity;
	}

}