/**
 * 
 */
package com.example.demo.services;

import org.springframework.stereotype.Component;

/**
 * 
 */
@Component
public class SingletonService {
	public SingletonService() {
		System.out.println("SingletonService Created");
	}
}
