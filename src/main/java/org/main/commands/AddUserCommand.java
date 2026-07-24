package org.main.commands;

import org.main.Bank;
import org.main.User;

public class AddUserCommand extends Command {

    public static final String NAME = "adduser";
    public static final String DESC = "Registers a new banking user.";
    public static final String USAGE = "adduser <name> <email> <phone>";
    private final Bank bank;

    private User lastAddedUser = null;

    @Override
    public String getName() { return NAME; }
    @Override
    public String getDescription() { return DESC; }
    @Override
    public String getUsage() { return USAGE; }

    public AddUserCommand(Bank bank) { this.bank = bank; }

    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: " + USAGE);
            return;
        }

        String name = args[0];
        String email = args[1];
        String phone = args[2];

        User user = new User(bank.getUsers().size() + 1, name, email, phone);
        bank.addUser(user);
        lastAddedUser = user;
    }

    @Override
    public void undo() {
        if (lastAddedUser != null) {
            bank.getUsers().remove(lastAddedUser);
            System.out.println("Undo: removed user " + lastAddedUser.getName());
        }
    }
}
