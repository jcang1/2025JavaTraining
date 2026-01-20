package m4.activity7;


public class InsufficientFundsException extends Exception {
    private final Double balance;
    private final Double requestedAmount;

    public InsufficientFundsException(String message, Double balance, Double requestedAmount) {
        super(message);
        this.balance = balance;
        this.requestedAmount = requestedAmount;
    }

    public Double getBalance() {
        return balance;
    }

    public Double getRequestedAmount() {
        return requestedAmount;
    }
}
