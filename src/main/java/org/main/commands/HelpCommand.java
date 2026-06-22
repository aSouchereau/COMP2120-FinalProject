package org.main.commands;

/**
 * Prints menu options and usage information for main program.
 */
public class HelpCommand implements ICommand {

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
