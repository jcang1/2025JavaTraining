package com.bpi.m6goupproject;

import com.bpi.m6groupproject.util.LibraryApplication;
import com.bpi.m6groupproject.util.*;

import jakarta.persistence.EntityManager;

public class Main {
	private static EntityManager em = EntityManagerUtil.getInstance().createEntityManager();

	public static void main(String[] args) {

		try {
			LibraryApplication libraryApplication = new LibraryApplication(em);
			libraryApplication.start();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.getInstance().closeEntityManager(em);
			EntityManagerUtil.getInstance().shutdownFactory();
		}

	}
}
