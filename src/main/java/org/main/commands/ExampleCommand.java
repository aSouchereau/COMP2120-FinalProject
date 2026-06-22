package org.main.commands;

public class ExampleCommand extends Command {

    int exampleCounter = 0;

    public static String name = "example";
    public static String description = "List command options and usage information.";
    public static String usage = "example <arg1> <arg2> <arg3> ...";

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
