package org.main;

import java.util.ArrayList;

public abstract class Account {
	private String accountNumber;
	private String customerName;
	protected double balance;
	
	private ArrayList<Transaction> transactions;
	
	
	public Account(String accountNumber, String customerName, double balance) {
		this.accountNumber = accountNumber;
		this.customerName = customerName;
		this.balance = balance;
		this.transactions = new ArrayList<>();
	}
	
	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit amount must be greater than 0");
		}
		
		balance += amount;
		
		transactions.add(new Transaction("Deposit", amount,"Deposit made to account" + accountNumber));
	}
	
	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Withdraw amount must be greater than 0");
		}
		
		if (amount > balance) {
			throw new IllegalArgumentException("Insufficient funds");
		}
		
		balance -= amount;
		
		transactions.add(new Transaction("Withdraw", amount,"Withdraw made from account" + accountNumber));
	}
	
	public void transfer(Account targetAccount, double amount) {
		if (targetAccount == null) {
			throw new IllegalArgumentException("Target accouunt cannot be null.");
		}
		
		this.withdraw(amount);
		targetAccount.deposit(amount);
		
		transactions.add(new Transaction("Transfer", amount, "Transferred $" + amount + "to account " + targetAccount.getAccountNumber()));
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	public abstract String getAccountType();
	
	@Override
	public String toString() {
		return "Account Number: " + accountNumber +
				"\nCustomer Name: " + customerName +
				"\nAccount Typer: " + getAccountType() +
				"\nBalance: $" + balance;
	}
	
}
