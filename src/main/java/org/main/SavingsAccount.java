package org.main;

public class SavingsAccount extends Account {
	private double intrestRate;
	
	public SavingsAccount(String accountNumber, User owner, double balance, double intrestRate) {
		super(accountNumber,owner,balance);
		this.intrestRate = intrestRate;
	}
	
	public void addIntrest() {
		double intrest = balance * intrestRate;
		balance += intrest;
		
		getTransactions().add(new Transaction("Intrest", intrest, "Intrest added to savings account " + getAccountNumber()));
	}
	
	public double getIntrestRate() {
		return intrestRate;
	}
	
	public void setIntrestRate(double intrestRate) {
		this.intrestRate = intrestRate;
	}
	
	@Override
	public String getAccountType() {
		return "Savings";
	}
	
	
}
