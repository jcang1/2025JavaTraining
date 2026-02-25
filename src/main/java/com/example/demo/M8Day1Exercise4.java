package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.service.BookService;

@SpringBootApplication
public class M8Day1Exercise4 implements CommandLineRunner {

	private final BookService bookService;

	public M8Day1Exercise4(BookService bookService) {
		this.bookService = bookService;
	}

	public static void main(String[] args) {
		SpringApplication.run(M8Day1Exercise4.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		try {
			bookService.bookLog();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
