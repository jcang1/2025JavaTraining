/**
 * 
 */
package ph.com.bpi.hello;

import java.util.Scanner;

/**
 * 
 */
public class BasicJavaActivity2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		System.out.println("Enter your age: ");
		Scanner input = new Scanner(System.in);
		
		String userInputString = input.nextLine();     // read input
		int userInputInt = Integer.parseInt(userInputString); //convert String to Int
		double userInputDouble = userInputInt;     //convert Int to double
		int older5 = userInputInt + 5;
		int younger2 = userInputInt + 2;
		int twice = userInputInt * 2;
		
		System.out.println("Your age as int: " + userInputInt);
		System.out.println("Your age as double: " + userInputDouble);
		System.out.println(" ");
		System.out.println("Arithmetic testing");
		System.out.println("He is 5 years older: " + older5);
		System.out.println("She is 2 years younger: " + younger2);
		System.out.println("Uncle is twice older: " + twice);

	}

}
