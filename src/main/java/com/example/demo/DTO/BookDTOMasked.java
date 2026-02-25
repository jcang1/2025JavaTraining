package com.example.demo.DTO;

public class BookDTOMasked {
	
	//private Long id;
	private String title;
	private String author;
	//private Boolean isAvailable;
	
	public BookDTOMasked() {
		
	}
	
	public BookDTOMasked (String title, String author) {
		//this.id = id;
		this.title = title;
		this.author = author;
		//this.isAvailable = true;
	}
	
	//public Long getId() { return id; }
	public String getTitle() { return title; }
	public String getAuthor() { return author; }
	//public Boolean getIsAvailable() { return isAvailable; }
	
	//public void setId(Long id) {
	//	this.id = id;
	//}
	
	public void setTitle(String title) {
		this.title = title;
	}
		
	public void setAuthor(String author) {
		this.author = author;
	}
	
	//public void setIsAvailable(Boolean isAvailable) {
	//	this.isAvailable = isAvailable;
	//}
		
}
