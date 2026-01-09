/**
 * 
 */
package ph.com.bpi.hello;

import java.util.Scanner;

/**
 * 
 */
public class BasicJavaActivity1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		System.out.println("Hello World");
		System.out.println("What is your name?");
		Scanner input = new Scanner(System.in);
		String userInput = input.nextLine();     // read input
		System.out.println("Hello " + userInput + "!");
	}

}
