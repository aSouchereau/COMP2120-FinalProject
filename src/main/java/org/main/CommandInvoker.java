package org.main;

import org.main.commands.ICommand;

import java.util.Stack;

public class CommandInvoker {
    private final Stack<ICommand> commandHistory = new Stack<ICommand>();

    /**
     * @param command the command to be executed
     * @param args  pass any number of String arguments
     */
    public void executeCommand(ICommand command, String... args) {
        command.execute(args);
        this.commandHistory.push(command);
    }

    /**
     * Removes executed command from the top of history stack, and calls its undo method
     */
    public void undoCommand() {
        ICommand command = commandHistory.pop();
        if (command != null) {
            command.undo();
        }
    }
}
