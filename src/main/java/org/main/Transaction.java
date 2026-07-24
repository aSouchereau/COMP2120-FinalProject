package org.main;

import java.io.Serializable;

public class Transaction implements Serializable {
	private String type;
	private double amount;
	private String description;
	
	public Transaction(String type, double amount, String description) {
		this.type = type;
		this.amount = amount;
		this.description = description;
	}
	
	
	public String getType() {
		return type;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return type + " | $" + amount + " | " + description;
	}
	
}
