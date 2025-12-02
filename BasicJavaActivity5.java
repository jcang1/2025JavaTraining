/**
 * 
 */
package ph.com.bpi.hello;

import java.util.Scanner;

/**
 * 
 */
public class BasicJavaActivity5 {

	/**
	 * @param args
	 */
	
	public static void loopSumTest() {
		System.out.print("Enter first integer: ");
		Scanner input1 = new Scanner(System.in);
		String userInputString1 = input1.nextLine();     // read input
		int userInputInt1 = Integer.parseInt(userInputString1); //convert String to Int
				
		
		System.out.print("Enter second integer: ");
		Scanner input2 = new Scanner(System.in);
		String userInputString2 = input2.nextLine();     // read input
		int userInputInt2 = Integer.parseInt(userInputString2); //convert String to Int
		
		int loopSum = 0;
		int j = userInputInt1;
        while (j <= userInputInt2) {
        	loopSum = loopSum + j;
        	j++;
        }
		
		System.out.println("Sum : " + loopSum);
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loopSumTest();
	}

}
