import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.main.Account;
import org.main.Bank;
import org.main.ChequingAccount;
import org.main.User;
import org.main.commands.SeedDataCommand;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Bank")
public class TestBank extends BaseTest {

    Faker faker = new Faker();

    @BeforeEach
    public void setup() {
        super.setup();
        SeedDataCommand command = new SeedDataCommand(mockBank);
        command.execute(new String[]{"2"});
    }

    @Test
    @DisplayName("should save and load bank data")
    public void testBankSerialization() {
        mockBank.save();
        Bank newBank = null;

        try {
            newBank = Bank.load(bankFilename);
        } catch (Exception e) {
            fail(e);
        } finally {
            assertEquals(newBank.getUsers(), mockBank.getUsers());
            assertEquals(newBank.getAccounts(), mockBank.getAccounts());
        }
    }

    @Test
    @DisplayName("should return the correct account")
    public void testGetAccountByNumber() {
        int userId = mockBank.getUsers().size() + 1;
        User user = new User(userId, faker.name().firstName(), faker.internet().emailAddress(), faker.phoneNumber().phoneNumber());
        mockBank.addUser(user);
        String accountNumber = "578439057";
        Account account = new ChequingAccount(accountNumber, user, 0, 0);
        mockBank.addAccount(account);

        Account result = mockBank.findAccountByNumber(accountNumber);

        assertEquals(account, result);
    }
}
