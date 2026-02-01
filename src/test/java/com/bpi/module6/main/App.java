package com.bpi.module6.main;

import com.bpi.module6.model.Student;
import com.bpi.module6.model.Course;
import com.bpi.module6.util.*;

import jakarta.persistence.EntityManager;

public class App {
	public static void main(String[] args) {
		EntityManager em = EntityManagerUtil.getInstance().createEntityManager();

		try {
			//runM6Activity3(em);
			persistOneToMany(em);
		} finally {
			EntityManagerUtil.getInstance().closeEntityManager(em);
			EntityManagerUtil.getInstance().shutdownFactory();
		}
	}

	static void persistOneToMany(EntityManager em) {
		em.getTransaction().begin();
		
		Student student1 = em.find(Student.class, 1L);
		
		Course newCourse = new Course();
		newCourse.setCourseName("Math");
		newCourse.setGrade("80");
		newCourse.setStudent(student1);
		
		em.persist(newCourse);
		
		em.getTransaction().commit();
	}

}
