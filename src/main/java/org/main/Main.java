package org.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Supplier;

import org.main.commands.AddUserCommand;
import org.main.commands.Command;
import org.main.commands.ExampleCommand;
import org.main.commands.HelpCommand;
import org.main.commands.AddAccountCommand;
import org.main.exceptions.InvalidSaveDataException;
import org.main.commands.DepositCommand;
import org.main.commands.WithdrawCommand;
import org.main.commands.TransferCommand;
import org.main.commands.ViewAccountCommand;

public class Main {
    private static final CommandInvoker invoker =  new CommandInvoker();
    private static final Map<String, Supplier<Command>> commandRegistry = new HashMap<>();
    private static final String saveDataFilename = "bankData.ser";

    /**
     * Map command names (string) to command constructors
     */
    private static void registerCommands(Bank bank) {
        commandRegistry.put(HelpCommand.NAME, () -> new HelpCommand(commandRegistry));
        commandRegistry.put(ExampleCommand.NAME, () -> new ExampleCommand(bank));
        commandRegistry.put(AddUserCommand.NAME, () -> new AddUserCommand(bank));
        commandRegistry.put("addaccount", () -> new AddAccountCommand(bank));
        commandRegistry.put("deposit", () -> new DepositCommand(bank));
        commandRegistry.put("withdraw", () -> new WithdrawCommand(bank));
        commandRegistry.put("transfer", () -> new TransferCommand(bank));
        commandRegistry.put("viewaccount", () -> new ViewAccountCommand(bank));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = null;
        try {
            bank = Bank.load(saveDataFilename);
        } catch (InvalidSaveDataException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        registerCommands(bank);

        System.out.println("Welcome to the Bank Management System");
        System.out.println("-------------------------------------");
        invoker.executeCommand(commandRegistry.get(HelpCommand.NAME).get(), new String[]{});

        if (args.length == 0) {
            while (true) {
                System.out.print("bms> ");
                String input = scanner.nextLine();

                String[] commandArgs = new String[]{};
                String[] command = input.split(" ", 2);
                if (command.length > 1) {
                    commandArgs = command[1].split(" ");
                }
                if (Objects.equals(command[0], "exit")) break;
                if (command[0].isEmpty()) continue;
                try {
                    invoker.executeCommand(commandRegistry.get(command[0]).get(),  commandArgs);
                } catch (NullPointerException e) {
                    System.out.println(command[0] + ": Command not found. Type \"help\" for a list of commands.");
                    continue;
                }
            }
        }

        System.out.println("Saving bank data...");
        bank.save();
    }
}