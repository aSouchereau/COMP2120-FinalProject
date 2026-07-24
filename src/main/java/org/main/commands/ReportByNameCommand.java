package org.main.commands;

import java.util.List;

import org.main.Account;
import org.main.Bank;
import org.main.ReportGenerator;

public class ReportByNameCommand extends Command {

    private Bank bank;

    public ReportByNameCommand(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String getName() {
        return "report-name";
    }

    @Override
    public String getDescription() {
        return "Generates a report for all accounts owned by a user.";
    }

    @Override
    public String getUsage() {
        return "report-name <customerName> <filename.txt>";
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: " + getUsage());
            return;
        }

        String name = args[0];
        String filename = args[1];

        List<Account> accounts = bank.getAccountsByName(name);

        try {
            ReportGenerator.generateUserReport(name, accounts, filename);
            System.out.println("Report generated: " + filename);
        } catch (Exception e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }

    @Override
    public void undo() {
        System.out.println("Report generation cannot be undone.");
    }
}
