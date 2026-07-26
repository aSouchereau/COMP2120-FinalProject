import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.main.CommandInvoker;
import org.main.User;
import org.main.commands.AddUserCommand;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAddUser extends BaseTest {

    CommandInvoker invoker;
    Faker faker = new Faker();

    @BeforeEach
    public void setup() {
        super.setup();
        invoker = new CommandInvoker();
    }

    @AfterEach
    public void cleanup() {
        super.cleanup();
        invoker = null;
    }

    @Test
    public void testAddUserCommand() {
        AddUserCommand command = new AddUserCommand(mockBank);

        String name = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String phone = faker.phoneNumber().phoneNumber();

        String[] args = new String[]{name, email, phone};
        invoker.executeCommand(command, args);

        User user = mockBank.getUsers().get(0);

        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(phone, user.getPhone());
    }
}
