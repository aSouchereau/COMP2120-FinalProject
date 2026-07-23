package org.main.commands;

import org.main.Account;
import org.main.Bank;

public class TransferCommand extends Command {

	@Override
	public String getName() {
		return "transfer";
	}
	
	@Override
	public String getDescription() {
		return "Transfers money between two accounts";
	}
	
	@Override
	public String getUsage() {
		return "transfer <fromAccount> <toAccount> <amount>";
	}
	
	@Override
	public void execute(String[] args) {
		
		if (args.length < 3) {
			System.out.println(getUsage());
			return;
		}
		
		String fromAccountNumber = args[0];
		String toAccountNumber = args[1];
		
		try {
			
			double amount = Double.parseDouble(args[2]);
			
			Account fromAccount = Bank.findAccountByNumber(fromAccountNumber);
			Account toAccount = Bank.findAccountByNumber(toAccountNumber);
			
			if (fromAccount == null) {
				System.out.println("Sending account not found");
				return;
			}		
			
			if (toAccount == null) {
				System.out.println("Receiving account not found");
				return;
			}
			
			fromAccount.transfer(toAccount, amount);
			
			System.out.println("Transfer successful.");
			System.out.println("Sending account balance: $" + fromAccount.getBalance());
			System.out.println("Receiving account balance: $" + toAccount.getBalance());
			
		} catch (NumberFormatException e) {
			System.out.println("Transfer amount must be a valid number.");
		} catch (IllegalArgumentException e) {
			System.out.println("Transfer failed: " + e.getMessage());
		}
		
	}
	
	@Override
	public void undo() {
		System.out.println("Undo is not implemented for transfers.");
	}
	
	
}
