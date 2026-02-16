package com.bpi.m7groupproject.model;

import java.util.ArrayList;
import java.util.List;

import com.bpi.m7groupproject.dto.BookDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", nullable = false, length = 50, columnDefinition = "VARCHAR(50)")
	private String title;

	@Column(name = "author", nullable = false, length = 50, columnDefinition = "VARCHAR(50)")
	private String author;

	@Column(name = "is_available", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
	private Boolean isAvailable = true;

	// Bidirectional OneToMany (optional but useful)
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, // cascade if you need to persist/remove loans via Book
			orphanRemoval = false // usually false for historical data
	)
	private List<Loan> loans = new ArrayList<>();

	public Long getBookID() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getAuthor() {
		return this.author;
	}

	public Boolean getBookIsAvailable() {
		return this.isAvailable;
	}

	public void setBookId(Long id) {
		this.id = id;
	}

	public void setBook(String title, String author) {
		this.title = title;
		this.author = author;
	}
	
	public void setBookIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public BookDTO toDTO() {
		BookDTO BookDTO = new BookDTO();
		BookDTO.setId(this.id);
		BookDTO.setTitle(this.title);
		BookDTO.setAuthor(this.author);
		BookDTO.setIsAvailable(this.isAvailable);

		return BookDTO;
	}
}
