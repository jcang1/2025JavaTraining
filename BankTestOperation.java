package m4.activity6;

@FunctionalInterface
interface BankTestOperation {
	void execute() throws InvalidAmountException, InsufficientFundsException;

}
