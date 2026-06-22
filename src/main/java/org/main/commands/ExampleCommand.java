package org.main.commands;

public class ExampleCommand extends Command {

    int exampleCounter = 0;

    @Override
    public String getName() {
        return "example";
    }

    @Override
    public String getDescription() {
        return "This is an example command.";
    }

    @Override
    public String getUsage() {
        return "example <argument1> <argument2> <argument3> ...";
    }

    @Override
    public void execute(String[] args) {
        // print each argument
        for (String arg : args) {
            System.out.println(arg);
        }
        exampleCounter++;
    }

    @Override
    public void undo() {
        exampleCounter--;
    }
}
