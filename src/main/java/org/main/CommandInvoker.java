package org.main;

import org.main.commands.Command;

import java.util.EmptyStackException;
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
        try {
            if (commandHistory.isEmpty()) {
                throw new EmptyStackException();
            }

            Command command = commandHistory.pop();
            command.undo();
        } catch (EmptyStackException e) {
            System.out.println("Nothing to undo");
        }
    }
}
