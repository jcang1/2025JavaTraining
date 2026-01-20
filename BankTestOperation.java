
@FunctionalInterface
interface BankTestOperation {
	void execute() throws InvalidAmountException, InsufficientFundsException;

}
