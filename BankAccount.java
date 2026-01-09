/**
 * 
 */
package m4.activity1;

/**
 * 
 */
public class BankAccount {

	/**
	 * @param args
	 */

	private static String accountName = null;

	private static String getAccountName(String accountNumber) throws Exception {
		if (accountNumber == "ACC-001") {
			accountName = "Juan Dela Cruz";
		} else {
			if (accountNumber == "ACC-002") {
				accountName = "Maria Santos";
			} else {
				throw new NullPointerException("Error: Account not found!");
			}
		}
		return accountName;

	}

	public static void testCase(String accountnumber) {
		System.out.println("\nLooking up account: " + accountnumber);
		try {
			System.out.println("Account holder: " + getAccountName(accountnumber));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("=== Bank Account Name Display ===");
		testCase("ACC-001");
		testCase("ACC-002");
		testCase("ACC-003");
		testCase("ACC-999");
		testCase(null);
		System.out.println("\n=== Program completed successfully! ===");
	}

}
