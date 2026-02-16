/**
 * 
 */
package com.bpi.m7groupproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import com.bpi.m7groupproject.controller.BookController;
//import com.bpi.m7groupproject.repository.BookRepository;
import com.bpi.m7groupproject.service.BookServiceImplAPI;
import com.bpi.m7groupproject.util.*;

import static spark.Spark.*;

/**
 * 
 */
public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// intialize entityManager;
		EntityManager em = EntityManagerUtil.getInstance().createEntityManager();

		// start server on port 8080
		port(8080);

		// initialize repository and BookService
		//BookRepository bookRepository = new BookRepository(em);
		BookServiceImplAPI bookServiceImplAPI = new BookServiceImplAPI(em);
		
		BookController bookController = new BookController(bookServiceImplAPI);
		bookController.registerRoutes();
		
		logger.info("Server started at port {}", port() );
	}

}
