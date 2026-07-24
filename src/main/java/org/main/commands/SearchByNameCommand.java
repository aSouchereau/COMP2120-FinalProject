package org.main.commands;

import java.util.List;

import org.main.Account;
import org.main.Bank;

public class SearchByNameCommand extends Command {

    private Bank bank;

    public SearchByNameCommand(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String getName() {
        return "search-name";
    }

    @Override
    public String getDescription() {
        return "Search for accounts by customer name.";
    }

    @Override
    public String getUsage() {
        return "search-name <customerName>";
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: " + getUsage());
            return;
        }

        String name = args[0];

        List<Account> results = bank.searchByCustomerName(name);

        if (results.isEmpty()) {
            System.out.println("No accounts found for name: " + name);
            return;
        }

        for (Account acc : results) {
            System.out.println(acc);
            System.out.println("----------------------");
        }
    }

    @Override
    public void undo() {
        System.out.println("Search command cannot be undone.");
    }
}
