package com.example.demo.services;

import org.springframework.context.ApplicationContext;

public class DemoService {
	private static SingletonService s1;
	private static SingletonService s2;
	private static PrototypeService p1;
	private static PrototypeService p2;
	
	public void createInstances(ApplicationContext context) {
		//Get Singleton Beans		
		s1 = context.getBean(SingletonService.class);
		s2 = context.getBean(SingletonService.class);
		
		//Get Prototype Beans
		p1 = context.getBean(PrototypeService.class);
		p2 = context.getBean(PrototypeService.class);		
	}
	
	public void print() {
		System.out.println("\nSingleton Same? " + (s1 == s2));
		System.out.println("\nPrototype Same? " + (p1 == p2));
	}

}
