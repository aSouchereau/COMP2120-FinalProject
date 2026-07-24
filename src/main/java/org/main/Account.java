package org.main;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Account implements Serializable {
	private String accountNumber;
	private User owner;
	protected double balance;
	
	private ArrayList<Transaction> transactions;
	
	// Constructor
	public Account(String accountNumber, User owner, double balance) {
		this.accountNumber = accountNumber;
		this.owner = owner;
		this.balance = balance;
		this.transactions = new ArrayList<>();
	}
	
	// Deposit Method
	public synchronized void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit amount must be greater than 0");
		}
		
		balance += amount;
		
		transactions.add(new Transaction("Deposit", amount," Deposit made to account" + accountNumber));
	}
	
	// Withdraw method
	public synchronized void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Withdraw amount must be greater than 0");
		}
		
		if (amount > balance) {
			throw new IllegalArgumentException("Insufficient funds");
		}
		
		balance -= amount;
		
		transactions.add(new Transaction("Withdraw", amount," Withdraw made from account" + accountNumber));
	}
	
	// Transfer between two accounts
	public synchronized void transfer(Account targetAccount, double amount) {
		if (targetAccount == null) {
			throw new IllegalArgumentException("Target account cannot be null.");
		}
		
		// Prevents sending money to the same account
		if (targetAccount == this) {
			throw new IllegalArgumentException("Cannot transfer to the same account");
		}
		
		this.withdraw(amount);
		targetAccount.deposit(amount);
		
		transactions.add(new Transaction("Transfer", amount, " Transferred $" + amount + " to account " + targetAccount.getAccountNumber()));
	}
	
	// Getters
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public double getBalance() {
		return balance;
	}
	
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	public abstract String getAccountType();
	
	
	// Prints list of transactions
	public void displayTransactions() {
		if (transactions.isEmpty()) {
			System.out.println("No transactions found.");
			return;
		}
		
		for (Transaction transaction : transactions) {
			System.out.println(transaction);
		}
		
	}
	
	
	@Override
	public String toString() {
		return "Account Number: " + accountNumber +
				"\nCustomer Name: " + owner.getName() +
				"\nAccount Type: " + getAccountType() +
				"\nBalance: $" + balance;
	}
	
}
