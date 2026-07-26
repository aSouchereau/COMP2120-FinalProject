import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.main.Bank;
import org.main.commands.SeedDataCommand;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Bank")
public class TestBank extends BaseTest {

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
}
