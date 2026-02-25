package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.controller.*;

@SpringBootApplication
public class DemoApplicationMain {
	
	private final BookController bookController;
	
	public DemoApplicationMain(BookController bookController) {
		this.bookController = bookController;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplicationMain.class, args);
	}

}
