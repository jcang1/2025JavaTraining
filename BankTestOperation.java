package m4.activity7;

@FunctionalInterface
interface BankTestOperation {
	void execute() throws InvalidAmountException, InsufficientFundsException;

}
