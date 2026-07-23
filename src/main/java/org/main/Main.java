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
import org.main.commands.DepositCommand;
import org.main.commands.WithdrawCommand;

public class Main {
    private static final CommandInvoker invoker =  new CommandInvoker();
    private static final Map<String, Supplier<Command>> commandRegistry = new HashMap<>();

    /**
     * Map command names (string) to command constructors
     */
    private static void registerCommands() {
        commandRegistry.put(HelpCommand.NAME, () -> new HelpCommand(commandRegistry));
        commandRegistry.put(ExampleCommand.NAME, ExampleCommand::new);
        commandRegistry.put("adduser", AddUserCommand::new);
        commandRegistry.put("addaccount", AddAccountCommand::new);
        commandRegistry.put("deposit", DepositCommand::new);
        commandRegistry.put("withdraw", WithdrawCommand::new);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        registerCommands();

        System.out.println("Welcome to the Bank Management System");
        System.out.println("-------------------------------------");
        invoker.executeCommand(commandRegistry.get(HelpCommand.NAME).get(), new String[]{});

        if (args.length == 0) {
            while (true) {
                System.out.print("bms> ");
                String input = scanner.nextLine();

                String[] commandArgs = new String[]{};
                String[] split = input.split(" ", 2);
                if (split.length > 1) {
                    commandArgs = split[1].split(" ");
                }
                if (Objects.equals(split[0], "exit")) break;
                try {
                    invoker.executeCommand(commandRegistry.get(split[0]).get(),  commandArgs);
                } catch (NullPointerException e) {
                    System.out.println(split[0] + ": Command not found. Type \"help\" for a list of commands.");
                    continue;
                }
            }
        }
    }
}