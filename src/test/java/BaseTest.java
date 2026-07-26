import org.junit.jupiter.api.BeforeEach;
import org.main.Bank;

public abstract class BaseTest {
    protected Bank mockBank;
    protected String bankFilename = "testBankData.ser";

    @BeforeEach
    public void setUp() {
        mockBank = new Bank(bankFilename);
    }

    protected void cleanUp() {
    }

}
