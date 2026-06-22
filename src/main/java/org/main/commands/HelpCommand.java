package org.main.commands;

/**
 * Prints menu options and usage information for main program.
 */
public class HelpCommand extends Command {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "List command options and usage information.";
    }

    @Override
    public String getUsage() {
        return "help";
    }

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
