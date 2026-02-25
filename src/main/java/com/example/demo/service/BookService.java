/**
 * 
 */
package com.example.demo.service;

import org.springframework.stereotype.Component;

/**
 * 
 */
@Component
public class BookService {
	
	private final LoggerService loggerService;
	
	//Constructor Injection
	public BookService(LoggerService loggerService) {
		this.loggerService = loggerService;
	}
	
	public void bookLog() {
		loggerService.log("\"Constructor Based\" test message BookService.java");
	}
	
}
