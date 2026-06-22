package org.main;

import org.main.commands.HelpCommand;

public class Main {
    private static final CommandInvoker invoker =  new CommandInvoker();

    public static void main(String[] args) {
        invoker.executeCommand(new HelpCommand());
        invoker.undoCommand();
    }
}