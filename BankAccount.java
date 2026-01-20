/**
 * 
 */
package m4.activity6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class BankAccount {
	
	private static final Logger logger = LoggerFactory.getLogger(M4Activity6.class);
	private Double initialBalance = 10000.0;
	private Double balance = initialBalance;
	
	public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
		logger.info("Withdrawal requested: ₱{" + amount + "}");
		
		if (amount <= 0) {
			logger.error("Invalid withdrawal amount: ₱{}", amount);
			throw new InvalidAmountException("Invalid withdrawal amount: " + amount);
		}
		
		if (amount > balance) {
			logger.warn("Insufficient funds. Balance: ₱{}, Requested: ₱{}", balance, amount);
			logger.error("Withdrawal failed: Insufficient funds for withdrawal");
			throw new InsufficientFundsException("Insufficient Fund ",balance, amount);
		}
		
		balance -= amount;
		logger.info("[INFO]Withdrawal completed: ₱{" + amount + "}, New balance: ₱{" + balance + "}");
		
	}
	
	public void deposit(double amount) throws InvalidAmountException  {
		logger.info("[INFO]Deposit requested: ₱{" + amount + "}");
		
		if (amount < 0) {
			logger.error("Invalid deposit amount: ₱{}", amount);
			logger.error("Deposit failed: deposit amount must be positive");
			throw new InvalidAmountException("Invalid deposit amount: " + amount);
		}
		
		if (amount > 50000) {
			logger.warn("Large deposit detected: ₱{}", amount);
		}
		
		balance += amount;
		logger.info("Deposit completed: ₱{" + amount + "}, New balance: ₱{" + balance + "}");
	}
	

	

}
