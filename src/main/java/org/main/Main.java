package org.main;

import org.main.commands.Command;
import org.main.commands.ExampleCommand;
import org.main.commands.HelpCommand;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Main {
    private static final CommandInvoker invoker =  new CommandInvoker();
    private static final Map<String, Supplier<Command>> commandRegistry = new HashMap<>();

    private static void registerCommands() {
        commandRegistry.put(HelpCommand.NAME, () -> new HelpCommand(commandRegistry));
        commandRegistry.put(ExampleCommand.NAME, ExampleCommand::new);
    }

    public static void main(String[] args) {
        registerCommands();
        invoker.executeCommand(commandRegistry.get(HelpCommand.NAME).get());
        invoker.undoCommand();
    }
}