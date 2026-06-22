package org.main.commands;

public class ExampleCommand extends Command {

    public static String NAME = "example";
    public static String DESC = "List command options and USAGE information.";
    public static String USAGE = "example <arg1> <arg2> <arg3> ...";

    @Override
    public String getName() { return NAME; }
    @Override
    public String getDescription() { return DESC; }
    @Override
    public String getUsage() { return USAGE; }

    // Command Specific Properties
    private int exampleCounter = 0;

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
