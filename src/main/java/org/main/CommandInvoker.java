package org.main;

import org.main.commands.Command;

import java.util.Stack;

public class CommandInvoker {
    private final Stack<Command> commandHistory = new Stack<>();

    /**
     * @param command the command to be executed
     * @param args  pass any number of String arguments
     */
    public void executeCommand(Command command, String[] args) {
        command.execute(args);
        this.commandHistory.push(command);
    }

    /**
     * Removes executed command from the top of history stack, and calls its undo method
     */
    public void undoCommand() {
        Command command = commandHistory.pop();
        if (command != null) {
            command.undo();
        }
    }
}
