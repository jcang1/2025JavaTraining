/**
 * 
 */
package com.bpi.main;
import java.util.Scanner;

/**
 * 
 */
public class StudentInfo {
	// Declaration of Attributes
	private String studentName;
	private int studentNumber;
	private int numberOfSubjects;
	private double[] scores;  // size depends on numberOfSubjects, all elements start as 0
	
	private double averageGrade;
	private String passedFailed;
	
	private int studentAddedFlag;
	private int studentAverageComputedFlag;
	// End declaration of Attributes	
	
	// Getter & Setter
    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
	
    public int getStudentNumber() {
        return this.studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }
    
	public int getNumberOfSubjects() {
        return this.numberOfSubjects;
    }

    public void setNumberOfSubjects(int numberOfSubjects) {
        this.numberOfSubjects = numberOfSubjects;
        // Allocate the scores array *after* we know the number of subjects
        this.scores = new double[numberOfSubjects];
    }
    
	public int getStudentAddedFlag() {
		return this.studentAddedFlag;
	}

	public void setStudentAddedFlag(int studentAddedFlag) {
		this.studentAddedFlag = studentAddedFlag;
	}

	public double getAverageGrade() {
        return this.averageGrade;
    }
	
	private void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }
	
	private void setPassedFailed(String passedFailed) {
        this.passedFailed = passedFailed;		
    }
	
	public String getPassedFailed() {
        return this.passedFailed;
    }
	
	public int getStudentAverageComputedFlag() {
		return this.studentAverageComputedFlag;
	}

	public void setStudentAverageComputedFlag(int studentAverageComputedFlag) {
		this.studentAverageComputedFlag = studentAverageComputedFlag;
	}
    
    //This portion will capture the grade
    public void captureScores() {
    	Scanner scanner = new Scanner(System.in);
    	
		int j = 0; //array position
		int k = 1; //display
		double value;
        while (j < this.numberOfSubjects) {
        	System.out.print("Enter grade for subject " + k + ": ");
        	
        	// validation of grade, should be 65 to 100
        	do {
        	value = scanner.nextDouble();
        		if ( value <65 || value > 100 ) {
        			System.out.println("Valid values are between 65 to 100");
        			System.out.print("Enter grade for subject " + k + ": ");
        		}
        	} while ( value <65 || value > 100 );
        	// end validation of grade, should be 65 to 100
        	
        	// stores the encoded score into array
    		scores[j] = value;
    		
            //System.out.println("score " + k + " = "  + scores[j]);
    		
        	j++;
        	k++;        	
        }
        System.out.println("===== STUDENT SAVED =====");
        
    }
    //End of capturing the grades

    //Computing the average grade and assess if passed/failed  
	public void computeAverageGrade() {
		int j = 0; //array position
		int k = 1; //display
		double sumCompute = 0;
		double averageComputed = 0;
        while (j < this.numberOfSubjects) {
        	sumCompute = sumCompute + scores[j];
            j++;
        	k++;
        }
		averageComputed = sumCompute / this.numberOfSubjects;
		double truncated = ((int)(averageComputed * 100)) / 100.0; // display up to 2 decimal places only
        setAverageGrade( truncated );
		if (truncated >= 75) {
			setPassedFailed("PASS");
		} else {setPassedFailed("FAIL");}
	}
    //Done computing average grade and assessing if passed/failed  
    
}
