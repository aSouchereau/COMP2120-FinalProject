package org.main.commands;

import org.main.Account;
import org.main.Bank;

public class DepositCommand extends Command {

	@Override
	public String getName() {
		return "deposit";
	}
	
	@Override
	public String getDescription() {
		return "Deposits money into an account.";
	}
	
	@Override
	public String getUsage() {
		return "deposit <accountNumber> <amount>";
	}
	
	@Override
	public void execute(String[] args) {
		
		if (args.length < 2) {
			System.out.println(getUsage());
			return;
		}
		
		String accountNumber = args[0];
		
		try {
			
			double amount = Double.parseDouble(args[1]);
			
			Account account = Bank.findAccountByNumber(accountNumber);
			
			if (account == null) {
				System.out.println("Account not found");
			}
			
			account.deposit(amount);
			
			System.out.println("Deposit successful.");
			System.out.println("New balance: $" + account.getBalance());
			
		} catch (NumberFormatException e) {
			System.out.println("Deposit must be a valid number.");
		} catch (IllegalArgumentException e) {
			System.out.println("Deposit failed: " + e.getMessage());
		}
			
	}
	
	@Override
	public void undo() {
		System.out.println("Undo is not implemented for deposits.");
	}
	
	
	
	
	
}
