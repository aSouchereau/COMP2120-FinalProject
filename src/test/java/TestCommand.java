import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.main.CommandInvoker;
import org.main.commands.Command;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestCommand extends BaseTest {

    private class EmptyCommand extends Command {
        protected ArrayList<String> dataDump = new  ArrayList<>();
        protected EmptyCommand(ArrayList<String> dataDump) { this.dataDump = dataDump; }
        @Override
        public String getName() {return "";}
        @Override
        public String getDescription() {return "";}
        @Override
        public String getUsage() {return "";}
        @Override
        public void execute(String[] args) {
            dataDump.addAll(Arrays.asList(args));
        }
        @Override
        public void undo() {
            dataDump.clear();
        }
    }

    CommandInvoker invoker;
    Faker faker = new Faker();

    @BeforeEach
    public void setup() {
        super.setup();
        invoker = new CommandInvoker();
    }

    @Test
    public void testCommandExecution() {
        String[] args = {
            faker.name().fullName(),
        };
        ArrayList<String> outputData = new ArrayList<>();
        EmptyCommand command = new EmptyCommand(outputData);
        invoker.executeCommand(command, args);

        assertEquals(args[0], outputData.get(0));
    }

    @Test
    public void testCommandUndo() {
        String[] args = {
                faker.name().fullName(),
        };
        ArrayList<String> outputData = new ArrayList<>();
        EmptyCommand command = new EmptyCommand(outputData);
        invoker.executeCommand(command, args);

        assertEquals(args[0], outputData.get(0));

        invoker.undoCommand();

        assertTrue(outputData.isEmpty());
    }
}
