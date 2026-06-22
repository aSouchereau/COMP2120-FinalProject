package org.main.commands;

public class HelpCommand extends Command {

    public static String name = "help";
    public static String description = "List command options and usage information.";
    public static String usage = "help";

    @Override
    public void execute(String[] args) {
        System.out.println("Command List:");
        System.out.println("--------------");
        System.out.println("\t- help");
        System.out.println("\t- exit");
    }

    /**
     * Intentionally left blank, nothing to undo
     */
    @Override
    public void undo() {}
}
