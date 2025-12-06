/**
 * 
 */
package com.bpi.main;

import java.util.Scanner;

/**
 * 
 */
public class StudentGradeManagementSystem {

	/**
	 * @param args
	 */
	static String pause;
	static StudentInfo newStudent = new StudentInfo();
	
    static class MenuChoice {
        final String mChoice;

        // Constructor name must match the class name
        MenuChoice(String mChoice) {
            this.mChoice = mChoice;
        }

        public String getMenuChoice() { return mChoice; }
    }
	
    // Use one Scanner shared across the program
    private static final Scanner SCANNER = new Scanner(System.in);    
    
	public static MenuChoice mainMenu() {
		System.out.println("===== STUDENT GRADING SYSTEM =====");
		System.out.println("A - Add Student Information       ");
		System.out.println("B - Compute Student Average       ");
		System.out.println("C - Display Student Information   ");
		System.out.println("D - Exit                          ");
		System.out.println("==================================");
		System.out.print("Enter Choice:                     ");
		
        String userChoice = SCANNER.nextLine();
        // Normalize input here (optional, can also do it in main)
        userChoice = userChoice.trim().toUpperCase();
        
        return new MenuChoice(userChoice);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		Scanner input1 = new Scanner(System.in);
		String userInput1 = input1.nextLine();     // read input
		System.out.println("User has chosen " + userInput1);
		
		StudentInfo newStudent = new StudentInfo();
		newStudent.setStudentName("Johnson Ang");
		newStudent.setStudentNumber(173716);
		System.out.println("Student Name : " + newStudent.getStudentName());
		System.out.println("Student ID : " + newStudent.getStudentNumber());
		
		newStudent.setNumberOfSubjects(5);
		newStudent.captureScores();
		*/
		MenuChoice userChoice;
		// Repeat menu until user selects D (Exit)
        do {
        	userChoice = mainMenu();

            switch (userChoice.getMenuChoice()) {
                case "A":
                	//System.out.println("===== A - Add Student Information =====");
                	
                	//StudentInfo newStudent = new StudentInfo();
                	newStudent.setStudentAddedFlag(0);						//Block option B and C
                	newStudent.setStudentAverageComputedFlag(0);			//Block option C
                	
                	System.out.println("Enter Student Name: ");
                	Scanner inputA1 = new Scanner(System.in);
            		String userInputA1 = inputA1.nextLine();				// read input
            		newStudent.setStudentName(userInputA1);					// storing student name 
            		
        			System.out.println("Enter Student Number: ");
                    Scanner inputA2 = new Scanner(System.in);
                    int userInputA2 = Integer.parseInt(inputA2.nextLine());	// read input and convert to integer
        			newStudent.setStudentNumber(userInputA2);				// storing student name
            		
        			//System.out.println("Student Name : " + newStudent.getStudentName());
        			//System.out.println("Student ID : " + newStudent.getStudentNumber());
        			
        			System.out.println("Enter number of subjects: ");
                    Scanner inputA3 = new Scanner(System.in);
                    int userInputA3 = Integer.parseInt(inputA3.nextLine());	// read input and convert to integer
        			newStudent.setNumberOfSubjects(userInputA3);			// storing number of subjects
                	
        			newStudent.captureScores();
        			newStudent.setStudentAddedFlag(1);						//1 allow option B and C
        			
        			System.out.println("==================================");
                	System.out.println("Press enter to return to Main Menu");
            		pause = SCANNER.nextLine();
            		
                    break;
                case "B":
                	//System.out.println("You have chosen B");
                	if (newStudent.getStudentAddedFlag() == 1) {
                		//System.out.println("Compute student average score and assess if passed or failed");
                		System.out.println(" ");
                		System.out.println(" ");
                		newStudent.computeAverageGrade();
                		System.out.println("Average: " + newStudent.getAverageGrade());
                		System.out.println("Status: " + newStudent.getPassedFailed());
                		newStudent.setStudentAverageComputedFlag(1);		//1 allow option C
                		
                	} else {
                		System.out.println("You need to encode the student information in option A!");
                	}
                	
                	System.out.println("==================================");
                	System.out.println("Press enter to return to Main Menu");
            		pause = SCANNER.nextLine();
            		
                    break;
                case "C":
                	//System.out.println("You have chosen C");
                	if (newStudent.getStudentAddedFlag() == 1 && newStudent.getStudentAverageComputedFlag() == 1) {
                		System.out.println("===== STUDENT SUMMARY =====");
                		System.out.println("Student Name: " + newStudent.getStudentName());
                		System.out.println("Student ID: " + newStudent.getStudentNumber());
                		System.out.println("Average Grade: " + newStudent.getAverageGrade());
                		System.out.println("Status: " + newStudent.getPassedFailed());
                		
                	} else {
                		System.out.println("You need to encode the student information in option A!");
                		System.out.println("You need trigger computation in option B!");
                	}
                	
                	System.out.println("==================================");
                	System.out.println("Press enter to return to Main Menu");
            		pause = SCANNER.nextLine();
            		
                    break;
                case "D":
                	System.out.println("You have chosen D: Exiting Program!");                	
                    break;    
                default:
                    System.out.println("Invalid Choice. Please enter A, B, C, or D.");
            }
        } while (!"D".equals(userChoice.getMenuChoice()));
        // Optional: close Scanner at the very end of the program
        SCANNER.close();        
        System.out.println("Program terminated. Goodbye!");
		
	}

}
