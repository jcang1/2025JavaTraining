/**
 * 
 */
package m4.activity2;

/**
 * 
 */
public class ATMSystem {
	
	private static double[] accounts = {10000,15000,20000};
	private static double balance = Double.MIN_VALUE;
	private static double amountInputD = Double.MIN_VALUE;
	private static double newBalance = Double.MIN_VALUE;
	
	private static String input1 = null;
	private static String input2 = null;
	
	private static void processWithdrawal(String accountIndex, String amountInput) {
		
		try {
			System.out.println("Account = " + accountIndex + ", Amount = " + amountInput);
			balance = accounts[Integer.parseInt(accountIndex)];
			amountInputD = Double.parseDouble(amountInput);
			//System.out.println("Account = " + accountIndex + ", Amount = " + amountInputD);
			System.out.println("Current balance: \u20B1" + String.format("%.2f", balance));
			System.out.println("Withdrawal: \u20B1" + String.format("%.2f", amountInputD));
			
			
			if (amountInputD > balance) {
				System.out.println("Insufficient funds! Cannot withdraw \u20B1" + String.format("%.2f", amountInputD));
			} else {
				newBalance = balance - amountInputD;
				//accounts[Integer.parseInt(accountIndex)] = newBalance;
				System.out.println("New balance: \u20B1" + String.format("%.2f", newBalance));
				System.out.println("Withdrawal successful!");
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Error: Invalid input!");
			System.out.println("Please enter a valid number.");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error: Account Not Found!");
			System.out.println("Invalid Account index.");
		} catch (Exception e) {			
			System.out.println("Transaction failed");
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("=== ATM Withdrawal System ===");
		System.out.println("\n---Test 1: Valid Withdrawal ---");
		input1 = "1";
		input2 = "5000";
		processWithdrawal(input1,input2);
		
		System.out.println("\n---Test 2: Invalid Account Index ---");
		input1 = "abc";
		input2 = "5000";
		processWithdrawal(input1,input2);
		
		System.out.println("\n---Test 3: Account not found ---");
		input1 = "10";
		input2 = "5000";
		processWithdrawal(input1,input2);
		
		System.out.println("\n---Test 4: Insufficient Funds ---");
		input1 = "1";
		input2 = "20000";
		processWithdrawal(input1,input2);
		
		//System.out.println("\n---Test 5: Amount not numeric ---");
		//input1 = "1";
		//input2 = "20000asa";
		//processWithdrawal(input1,input2);
		
		System.out.println("\n=== All tests completed! ===");
	}

}
