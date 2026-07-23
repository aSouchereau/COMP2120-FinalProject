package org.main.commands;

import org.main.Account;
import org.main.Bank;
import org.main.ChequingAccount;
import org.main.SavingsAccount;
import org.main.User;

public class AddAccountCommand extends Command {
	
	@Override
	public String getName() {
		return "addaccount";
	}
	
	@Override
	public String getDescription() {
		return "Creates a new bank account";	
	}
	
	@Override
	public String getUsage() {
		return "addaccount <accountNumber> <userId> <chequing/savings> <balance> <overdraftLimit/interestRate>";
	}
	
	
	
	@Override
	public void execute(String[] args) {
		
		if (args.length < 5) {
			System.out.println(getUsage());
			return;
		}
		
		String accountNumber = args[0];
		String accountType = args[2];
	
		
		try { 
		
		int userId = Integer.parseInt(args[1]);
		double startingBalance = Double.parseDouble(args[3]);
		double accountOption = Double.parseDouble(args[4]);
		
		User owner = Bank.findUserById(userId);
		
		if (owner == null) {
			System.out.println("User not found.");
			return;
		}
		
		Account account;
		
		
		if (accountType.equalsIgnoreCase("chequing")) {
			account = new ChequingAccount(accountNumber, owner, startingBalance, accountOption);
		}
		
		else if (accountType.equalsIgnoreCase("savings")) {
			account = new SavingsAccount(accountNumber, owner, startingBalance, accountOption);
		}
		
		else {
			System.out.println("Account type must be chequing or savings.");
			return;
		}
		
		Bank.addAccount(account);
		
		System.out.println("Account successfully created.");
		
		} catch(NumberFormatException e) {
			System.out.println("Balance and overdraft limit/interest rate must be a valid number.");
		}
	}
	
	
	@Override
	public void undo() {
		System.out.println("Undo is not implemented for account creation.");
	}
	
	
}
