package org.main.commands;

import org.main.Account;
import org.main.Bank;

public class WithdrawCommand extends Command {

	Bank bank;

	@Override
	public String getName() {
		return "withdraw";
	}
	
	@Override
	public String getDescription() {
		return "Withdraws money from an account.";
	}
	
	@Override
	public String getUsage() {
		return "withdraw <accountNumber> <amount>";
	}

	public WithdrawCommand(Bank bank) { this.bank = bank; }

	@Override
	public void execute(String[] args) {
		
		if (args.length < 2) {
			System.out.println(getUsage());
			return;
		}
		
		String accountNumber = args[0];
		
		try {
			
			double amount = Double.parseDouble(args[1]);
			
			Account account = bank.findAccountByNumber(accountNumber);
			
			if (account == null) {
				System.out.println("Account not found");
				return;
			}
			
			account.withdraw(amount);
			
			System.out.println("Withdrawal successful.");
			System.out.println("New balance: $" + account.getBalance());
			
		} catch (NumberFormatException e) {
			System.out.println("Withdrawal amount must be a valid number.");
		} catch (IllegalArgumentException e ) {
			System.out.println("Withdrawal failed: " + e.getMessage());
		}
		
	}
	
	@Override
	public void undo() {
		System.out.println("Undo is not implemented for withdrawals.");
	}
	
}
