package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/welcome")
public class DemoController {
	
	@Value("${app.msg}")
	private String message;
	
	@GetMapping
	@ResponseBody
	public String initialMessage() {
		return message;		
	}
	
}
