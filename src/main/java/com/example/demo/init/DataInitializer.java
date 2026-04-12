/**
 * Initial loading of users, if username is existing create will not proceed.
 */
package com.example.demo.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.services.UserService;

/**
 * 
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    
    public DataInitializer(UserService userService) {
    	this.userService = userService;
    }
    
    @Override
    public void run(String... args) {
        try {
			userService.createUser("librarian_01", "admin123!", "ADMIN");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        try {
			userService.createUser("librarian_02", "admin123!", "ADMIN");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        try {
			userService.createUser("member_01", "user_123!", "USER");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        try {
			userService.createUser("member_02", "user_123!", "USER");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        try {
			userService.createUser("member_03", "user_123!", "USER");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
