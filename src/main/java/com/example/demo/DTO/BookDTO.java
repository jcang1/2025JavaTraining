/**
 *  Added DTO to ensure the entity is not accessible from controller
 */
package com.example.demo.DTO;

import java.time.LocalDate;

/**
 * 
 */
public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private LocalDate publishedDate;
    private LocalDate libraryRecordCreatedDate;
    private boolean readFlag;
    private Long userId; // reference instead of User entity

    public BookDTO() {}

    // getters and setters

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
 
    public LocalDate getPublishedDate() {
        return publishedDate;
    }
 
    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }
 
    public LocalDate getLibraryRecordCreatedDate() {
        return libraryRecordCreatedDate;
    }
 
    public void setLibraryRecordCreatedDate(LocalDate libraryRecordCreatedDate) {
        this.libraryRecordCreatedDate = libraryRecordCreatedDate;
    }
 
    public boolean isReadFlag() {
        return readFlag;
    }
 
    public void setReadFlag(boolean readFlag) {
        this.readFlag = readFlag;
    }

    public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
    }
	
}
