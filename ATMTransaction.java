/**
 * 
 */
package m4.activity3;

/**
 * 
 */
public class ATMTransaction {

	/**
	 * @param args
	 */

	private static void checkBalance(String accountNumber, double balance) {
		try {
			System.out.println("Processing balance inquiry...");

			Integer.parseInt(accountNumber);
			//System.out.println("Account Number: " + accountNumber);

			char accountType = accountNumber.charAt(0);
			//char ST = 1;
			//char IM = 2;
			
			//System.out.println("Account accountType: " + accountType);
			
			if (accountType == '1') {
				System.out.println("Account Type: Savings");
			} else {
				if (accountType == '2') {
					System.out.println("Account Type: Checking");
				} else {
					throw new Exception("Account Type: Unkown");

				}
			}
			
			System.out.println("Account Number: " + accountNumber);
			System.out.println("Current balance: \u20B1" + String.format("%.2f", balance));
			System.out.println("Balance inquiry successful!");
			

		} catch (NumberFormatException e) {
			System.out.println("Error: Invalid account number format! Account numbers must be numeric.");
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Error: Account number is empty or invalid!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("\n========== RECEIPT ==========");
			System.out.println("Transaction Date: December 3, 2025");
			System.out.println("Transaction Type: Balance Inquiry");
			System.out.println("ATM Location: Main Branch");
			System.out.println("Thank you for banking with us!");
			System.out.println("==============================\n");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("--- ATM BALANCE INQUIRY SYSTEM ---\n");
		System.out.println("--- Test Case 1: Valid Savings Account ---");
		checkBalance("100123456", 15000.00);
		System.out.println("--- Test Case 2: Valid Checking Account ---");
		checkBalance("200987654", 25000.00);
		System.out.println("--- Test Case 3: Invalid Account Number Format ---");
		checkBalance("ABC12345", 15000.00);
		System.out.println("--- Test Case 4: Empty Account Number ---");
		checkBalance("", 15000.00);
	}

}
