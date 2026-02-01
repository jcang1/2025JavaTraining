package com.bpi.module6.main;

import com.bpi.module6.model.Student;
import com.bpi.module6.model.Course;
import com.bpi.module6.util.*;

import jakarta.persistence.EntityManager;

public class App {
	public static void main(String[] args) {
		EntityManager em = EntityManagerUtil.getInstance().createEntityManager();

		try {
			// runM6Activity3(em);
			m6Activity4Solution(em);
		} finally {
			EntityManagerUtil.getInstance().closeEntityManager(em);
			EntityManagerUtil.getInstance().shutdownFactory();
		}
	}

	static void m6Activity4Solution(EntityManager em) {

		em.getTransaction().begin();

		// 1. create Student object, assign values
		Student newStudent = new Student(); // transient
		newStudent.setName("Pedro Reyes");
		newStudent.setAge(23);
		newStudent.setEmail("pedroreyes@gmail.com");
		// 2. attach transient student object to persistence context
		em.persist(newStudent);
		// 3. call flush()
		em.flush();
		// 4. detach the managed newStudent from the persistence context
		em.detach(newStudent);
		// 5. print "is newStudent inside the persistence context: " + call contains()
		System.out.println("is newStudent inside the persistence context: " + em.contains(newStudent));

		// 6. reattach the detached newStudent
		newStudent = em.merge(newStudent);
		// 7. update newStudent, change some values like age or email
		newStudent.setAge(24);
		newStudent.setEmail("pedro.reyes.ph@gmail.com");
		// 8. call flush()
		em.flush();
		// 9. print "is newStudent inside the persistence context: " + call contains()
		System.out.println("is newStudent inside the persistence context: " + em.contains(newStudent));
		// 11. mark managed newStudent for deletion
		em.remove(newStudent);
		// 12. call flush()
		em.flush();
		// 13. print "is newStudent inside the persistence context: " + call contains()
		System.out.println("is newStudent inside the persistence context: " + em.contains(newStudent));
		em.getTransaction().commit();
	}

}
