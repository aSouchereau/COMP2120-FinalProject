package org.main;

public class SavingsAccount extends Account {
	private double interestRate;
	
	public SavingsAccount(String accountNumber, User owner, double balance, double interestRate) {
		super(accountNumber,owner,balance);
		this.interestRate = interestRate;
	}
	
	public void addInterest() {
		double interest = balance * interestRate;
		balance += interest;
		
		getTransactions().add(new Transaction("Interest", interest, "Interest added to savings account " + getAccountNumber()));
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	@Override
	public String getAccountType() {
		return "Savings";
	}
	
	
}
