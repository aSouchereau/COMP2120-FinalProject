package org.main.commands;

import net.datafaker.Faker;
import org.main.*;

import java.util.ArrayList;

public class SeedDataCommand extends Command {

    Bank bank;

    @Override
    public String getName() {
        return "seeddata";
    }

    @Override
    public String getDescription() {
        return "Seed the bank with fake data for demonstration.";
    }

    @Override
    public String getUsage() {
        return "seeddata <userCount>";
    }

    public SeedDataCommand(Bank bank) { this.bank = bank; }

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Account> accounts = new ArrayList<>();

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: " + getUsage());
            return;
        }

        int userCount = 0;
        try {
            userCount = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid value for argument 'userCount'");
            System.out.println("Usage: " + getUsage());
        }

        Faker faker = new Faker();
        int userId = bank.getUsers().size() + 1;
        for (int i = 0; i < userCount; i++) {
            String name = faker.name().firstName();
            String email = faker.internet().emailAddress();
            String phone = faker.phoneNumber().phoneNumber();
            User user = new User(userId++, name, email, phone);
            users.add(user);
            bank.addUser(user);

            int cAccountNumber = bank.getAccounts().size() + 1;
            int balance = faker.number().numberBetween(1, 10000);
            int limit = faker.number().numberBetween(1, 1000);
            ChequingAccount chequingAccount = new ChequingAccount(Integer.toString(cAccountNumber), user, balance, limit);
            accounts.add(chequingAccount);
            bank.addAccount(chequingAccount);

            int sAccountNumber = bank.getAccounts().size() + 1;
            int savingsBalance = faker.number().numberBetween(1, 10000);
            double rate = faker.number().numberBetween(0, 15);
            SavingsAccount savingsAccount = new SavingsAccount(Integer.toString(sAccountNumber), user, savingsBalance, rate / 100);
            accounts.add(savingsAccount);
            bank.addAccount(savingsAccount);

        }

    }

    @Override
    public void undo() {
    }


}
