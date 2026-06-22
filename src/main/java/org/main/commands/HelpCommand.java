package org.main.commands;

import java.util.Map;
import java.util.function.Supplier;

public class HelpCommand extends Command {

    public static String NAME = "help";
    public static String DESC = "List command options and USAGE information.";
    public static String USAGE = "help";

    @Override
    public String getName() { return NAME; }
    @Override
    public String getDescription() { return DESC; }
    @Override
    public String getUsage() { return USAGE; }

    private final Map<String, Supplier<Command>> commandRegistry;

    public HelpCommand(Map<String, Supplier<Command>> registry) {
        commandRegistry = registry;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Command List:");
        for (Map.Entry<String, Supplier<Command>> entry : commandRegistry.entrySet()) {
            Command command = entry.getValue().get();
            System.out.println("\t " + command.getName() + " - Usage: " + command.getUsage());
            System.out.println("\t\t" + command.getDescription() + "\n");
        }
    }

    /**
     * Intentionally left blank, nothing to undo
     */
    @Override
    public void undo() {}
}
