package com.bpi.module6.main;

import com.bpi.module6.model.Student;

import java.util.List;

import com.bpi.module6.model.Course;
import com.bpi.module6.util.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class App {
	private static EntityManager em = EntityManagerUtil.getInstance().createEntityManager();
	
	public static void main(String[] args) {
		//EntityManager em = EntityManagerUtil.getInstance().createEntityManager();

		try {
			// runM6Activity3(em);
			System.out.println("Executing findStudentNames");
			findStudentNames();
			System.out.println("Done Executing findStudentNames");

			System.out.println("\nExecuting countCoursesByStudentId");
			Long id = Long.parseLong("18");
			countCoursesByStudentId(id);
			System.out.println("Done Executing countCoursesByStudentId");

			System.out.println("\n Executing findStudentsByAgeGreaterThan");
			findStudentsByAgeGreaterThan(20);
			System.out.println("Done executing findStudentsByAgeGreaterThan");
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			EntityManagerUtil.getInstance().closeEntityManager(em);
			EntityManagerUtil.getInstance().shutdownFactory();

		}
	}
	
	static void findStudentNames() {
		em.getTransaction().begin();

		String jpql = "Select s FROM Student s";
	
		TypedQuery<Student> query = em.createQuery(jpql, Student.class);
		List<Student> students = query.getResultList();

		// print student names
		students.forEach(student -> System.out.println(student.getName()));

		em.getTransaction().commit();

	}

	static void countCoursesByStudentId(Long id) {
		em.getTransaction().begin();

		String jpql = "Select count(c) from Course c Where c.student.id = ?1";

		Long count = em.createQuery(jpql, Long.class).setParameter(1, id).getSingleResult();

		System.out.println("Course count for student id = " + id + " is " + count);
		em.getTransaction().commit();
	}

	static void findStudentsByAgeGreaterThan(int age) {
		em.getTransaction().begin();

		String jpql = "Select count(s) FROM Student s where s.age > ?1";
		//TypedQuery<Student> query = em.createQuery(jpql, Student.class);
		Long count = em.createQuery(jpql, Long.class).setParameter(1, age).getSingleResult();


		// print student names
		System.out.println("Number of students age greater than " + age + " is " + count);
		//students.forEach(student -> System.out.println(student.getName() + "|" + student.getAge()));

		em.getTransaction().commit();
	}
}
