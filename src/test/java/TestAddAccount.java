import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.main.Account;
import org.main.CommandInvoker;
import org.main.User;
import org.main.commands.AddAccountCommand;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAddAccount extends BaseTest {

    CommandInvoker invoker;
    Faker faker = new Faker();
    User owner = new User(1000, "TestUser", "test@example.com", "5199999999");

    @BeforeEach
    public void setup() {
        super.setup();
        invoker = new CommandInvoker();
        mockBank.addUser(owner);
    }

    @AfterEach
    public void cleanup() {
        super.cleanup();
        invoker = null;
    }

    @Test
    public void testAddAccountCommand() {
        AddAccountCommand command = new AddAccountCommand(mockBank);

        String accountNumber = "10001";
        String option = "Chequing";
        String balance = "1";
        String overdraft = "1";

        String[] args = new String[]{accountNumber, Integer.toString(owner.getUserId()), option, balance, overdraft};
        invoker.executeCommand(command, args);

        Account account = mockBank.getAccounts().get(0);

        assertEquals(accountNumber, account.getAccountNumber());
        assertEquals(owner, account.getOwner());
        assertEquals(option, account.getAccountType());
    }
}
