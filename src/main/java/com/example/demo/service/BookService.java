/**
 * 
 */
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service
public class BookService {
	
	@Autowired
	private LoggerService loggerService;
	
	public void bookLog() {
		loggerService.log("\"Field Injection\" test message from BookService.java");
	}
	
}
