import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.main.ChequingAccount;
import org.main.CommandInvoker;
import org.main.User;
import org.main.commands.DepositCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDeposit extends BaseTest {

    CommandInvoker invoker;
    User owner = new User(1000, "TestUser", "test@example.com", "5199999999");
    ChequingAccount account = new ChequingAccount("1001", owner, 0, 0);

    @BeforeEach
    public void setup() {
        super.setup();
        invoker = new CommandInvoker();
        mockBank.addUser(owner);
        mockBank.addAccount(account);
    }

    @AfterEach
    public void cleanup() {
        super.cleanup();
        invoker = null;
    }

    @Test
    public void testDepositCommand() {
        DepositCommand command = new DepositCommand(mockBank);

        String[] args = new String[]{account.getAccountNumber(), "20"};
        invoker.executeCommand(command, args);

        assertEquals(account.getBalance(), 20);

    }
}
