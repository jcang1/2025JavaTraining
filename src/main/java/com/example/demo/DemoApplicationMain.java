package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.controller.*;

@SpringBootApplication
public class DemoApplicationMain {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplicationMain.class, args);
		
		BookController bookController = new BookController();
		bookController.initialBooks();
	}

}
