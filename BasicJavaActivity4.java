/**
 * 
 */
package ph.com.bpi.hello;

import java.util.Scanner;

/**
 * 
 */
public class BasicJavaActivity4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		System.out.println("Enter your age: ");
		Scanner input1 = new Scanner(System.in);
		String userInputString1 = input1.nextLine();     // read input
		int userInputInt1 = Integer.parseInt(userInputString1); //convert String to Int
		
		
		if (userInputInt1 >= 60) {
			System.out.println("Senior! Enjoy discounts. <3");
		} else {
		if (userInputInt1 >= 18) {
			System.out.println("Adult! Work hard, play harder. :)");
		} else {
			System.out.println("Minor, focus on studies!");
		}}

	}

}
