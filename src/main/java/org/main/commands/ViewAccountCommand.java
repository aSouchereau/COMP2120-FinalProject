package org.main.commands;

import org.main.Account;
import org.main.Bank;

public class ViewAccountCommand extends Command {

	@Override
	public String getName() {
		return "viewaccount";
	}
	
	@Override 
	public String getDescription() {
		return "Displays account information and balance.";
	}
	
	@Override
	public String getUsage() {
		return "viewaccount <accountNumber>";
	}
	
	@Override
	public void execute(String[] args) {
		
		if (args.length < 1) {
			System.out.println(getUsage());
			return;
		}
		
		String accountNumber = args[0];
		
		Account account = Bank.findAccountByNumber(accountNumber);
		
		if (account == null) {
			System.out.println("Account not found.");
			return;
		}
		
		System.out.println(account);
		
	}
	
	@Override
	public void undo() {
		System.out.println("Undo not applicable for viewing an account.");
	}
	
	
}
