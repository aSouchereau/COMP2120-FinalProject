package org.main;

public class ChequingAccount extends Account {
	//gives a max amount that your account can fall below $0
	private double overdraftLimit;
	
	public ChequingAccount(String accountNumber, String customerName, double balance, double overdraftLimit) {
		super(accountNumber, customerName, balance);
		this.overdraftLimit = overdraftLimit;
	}
	
	@Override
	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Withdrawal amount must be greater than 0.");
		}
		
		if (amount > balance + overdraftLimit) {
			throw new IllegalArgumentException("Withdrawal exceeds overdraft limit.");
		}
		
		balance -= amount;
		
		getTransactions().add(new Transaction("Withdraw", amount, "Withdrawa; made from chequing account " + getAccountNumber()));
		
	}
	
	public double getOverdraftLimit() {
		return overdraftLimit;
	}
	
	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}
	
	@Override
	public String getAccountType() {
		return "Chequing";
	}
	
	
}
