package com.bpi.module6.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "course_name", nullable = false, length = 50, columnDefinition = "VARCHAR(50)")
	private String courseName;
	
	@Column(name = "grade", nullable = false, length = 2, columnDefinition = "VARCHAR(2)")
	private String grade;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id")
	private Student student;

	//getters and setters
	public Long getId() {
		return this.id;
	}
	
	public String getCourseName() {
		return this.courseName;
	}
	
	public String getGrade() {
		return this.grade;
	}
	
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}

}
