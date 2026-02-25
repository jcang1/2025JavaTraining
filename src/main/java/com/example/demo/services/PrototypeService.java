package com.example.demo.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeService {
	public PrototypeService() {
		System.out.println("PrototypeService Created");
	}
}