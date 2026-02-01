package com.bpi.module6.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, length = 50, columnDefinition = "VARCHAR(50)")
	private String name;

	@Column(name = "age", columnDefinition = "INT")
	private int age;

	@Column(name = "email", unique = true, length = 100, columnDefinition = "VARCHAR(100)")
	private String email;

	//getters and setters
	public void setName(String studentName) {
		// TODO Auto-generated method stub
		this.name = studentName;
	}
	
	public void setAge(int studentAge) {
		this.age = studentAge;
	}
	
	public void setEmail(String studentEmail) {
		this.email = studentEmail;
	}

}
