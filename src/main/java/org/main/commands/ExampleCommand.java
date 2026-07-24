package org.main.commands;

import org.main.Bank;

public class ExampleCommand extends Command {

    public static String NAME = "example";
    public static String DESC = "Example command description";
    public static String USAGE = "example <arg1> <arg2> <arg3> ...";

    private final Bank bank;

    @Override
    public String getName() { return NAME; }
    @Override
    public String getDescription() { return DESC; }
    @Override
    public String getUsage() { return USAGE; }

    public ExampleCommand(Bank bank) { this.bank = bank; }

    // Command Specific Properties
    private int exampleCounter = 0;

    @Override
    public void execute(String[] args) {
        // print each argument
        for (String arg : args) {
            System.out.println(arg);
        }
        exampleCounter++;

        System.out.println(bank.getUsers());
        System.out.println(bank.getAccounts());

    }

    @Override
    public void undo() {
        exampleCounter--;
    }
}
