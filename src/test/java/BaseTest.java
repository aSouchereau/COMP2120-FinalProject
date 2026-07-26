import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.main.Bank;

public abstract class BaseTest {
    protected Bank mockBank;
    protected String bankFilename = "testBankData.ser";

    @BeforeEach
    public void setup() {
        mockBank = new Bank(bankFilename);
    }

    @AfterEach
    protected void cleanup() {
        mockBank = null;
    }

}
