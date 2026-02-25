/**
 * 
 */
package com.example.demo.service;

import org.springframework.stereotype.Service;

/**
 * 
 */
@Service
public class LoggerService {
	
	public void log(String msg) {
		System.out.println("\nM8_Day1_Exercise3: " + msg + "\n");
	}

}
