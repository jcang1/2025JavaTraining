/**
 * 
 */
package m5.exercise3;
/**
 * 
 */
import java.util.Scanner;
/**
 * 
 */
public class Menu extends Exception {
    private static final Scanner SCANNER = new Scanner(System.in);
    private String userChoice;
    private boolean initialScreen = true;
    
    StudentsDB stud = new StudentsDB();
    CoursesDB course = new CoursesDB();
    
    public void displayMenu() {
    	
    	if (initialScreen) {
    		initialScreen = false;
    	} else {
    		System.out.println("Press enter to return to main menu.");
    		SCANNER.nextLine();
    	}
    	
    	System.out.println("==================================================");
    	System.out.println("=====  STUDENT COURSE MANAGEMENT             =====");
    	System.out.println("==----------------------------------------------==");
    	System.out.println("==  1. Add Student                              ==");
    	System.out.println("==  2. Add Course                               ==");
    	System.out.println("==  3. Show Students                            ==");
    	System.out.println("==  4. Show Courses                             ==");
    	System.out.println("==  0. Exit                                     ==");
    	System.out.println("==================================================");
    	System.out.println("==  Choose an Option:                           ==");
    	System.out.println("==================================================");
    	
    	String menuChoice = SCANNER.nextLine();
    	
    	this.userChoice = menuChoice;
    }
    
    public String getMenuChoice() {
    	return this.userChoice;
    }
    
    public void option1() {
    	System.out.println("Enter name:");
    	String input1 = SCANNER.nextLine();
    	
    	System.out.println("Enter age:");
    	int input2 = Integer.parseInt(SCANNER.nextLine());
    	
    	System.out.println("Enter email:");
    	String input3 = SCANNER.nextLine();
    	
    	stud.addStudent(input1, input2, input3);
    }
    
    public void option2() {
    	System.out.println("Enter Student ID:");
    	int input1 = Integer.parseInt(SCANNER.nextLine());
    	
    	System.out.println("Enter course name");
    	String input2 = SCANNER.nextLine();
    	
    	System.out.println("Enter grade");
    	String input3 = SCANNER.nextLine();
    	
    	course.addStudentGrade(input1, input2, input3);
    }
    
    public void option3(){
    	System.out.println("All Students!");
    	
    	stud.inqStudent();
    }
    
    public void option4() {
    	System.out.println("All Courses!");
    	
    	course.inqCourses();
    }
    
    public void option0() {
    	System.out.println("Exiting application. Goodbye!");
    }
    
    public 	void defaultOption() {
		System.out.println("Invalid entry. Please choose from the available options.");
	}
}
