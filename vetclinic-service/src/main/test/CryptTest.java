import org.junit.Test;
import util.CryptManager;

import static org.junit.Assert.assertTrue;

public class CryptTest {

    @Test
    public void equalTest() {

        String password = "password";

        assertTrue(
                CryptManager.matchesPasswords(
                        password,
                        CryptManager.getCryptPassword(password)
                )
        );
    }
}