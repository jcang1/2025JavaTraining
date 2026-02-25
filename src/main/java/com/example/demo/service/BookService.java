/**
 * 
 */
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 */
@Component
public class BookService {
	
	private final LoggerService loggerService;
	
	//Constructor Injection
	@Autowired
	public BookService(LoggerService loggerService) {
		this.loggerService = loggerService;
	}
	
	public void bookLog() {
		loggerService.log("\"Setter Injection\" test message from BookService.java");
	}
	
}
