package m4.activity6;


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


public class M4Activity6 {

	private static void runTest(BankTestOperation operation, String operationName) {
		try {

			operation.execute();

		} catch (InvalidAmountException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InsufficientFundsException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

        //Logger logger = LoggerFactory.getLogger(M4Activity6.class);
        //logger.info("SLF4J is working.");

		BankAccount account = new BankAccount();

        runTest(() -> account.deposit(5000),      "Deposit");      // Test 1
        runTest(() -> account.withdraw(3000),     "Withdrawal");   // Test 2
        runTest(() -> account.deposit(-500),      "Deposit");      // Test 3
        runTest(() -> account.withdraw(20000),    "Withdrawal");   // Test 4
        runTest(() -> account.deposit(60000),     "Deposit");      // Test 5

	}

}
