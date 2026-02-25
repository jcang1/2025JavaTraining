package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.services.*;

@SpringBootApplication
public class M8Day1Exercise6 {

	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(M8Day1Exercise6.class, args);
		
		DemoService demoService = new DemoService();
		demoService.createInstances(context);
		demoService.print();
	}

}
