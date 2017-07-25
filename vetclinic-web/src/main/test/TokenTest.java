import app.SpringApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import security.TokenHandler;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringApp.class})
@WebAppConfiguration
@TestPropertySource("/application.properties")
public class TokenTest {

    @Autowired
    @Qualifier("tokenHandler")
    private TokenHandler tokenHandler;

    @Test
    public void tokenTest() {
        String token = tokenHandler.generateAccessToken("user@user.ru", LocalDateTime.now().plusDays(14));
        String extractId = tokenHandler.extractId(token).get();

        assertTrue(extractId.equals("user@user.ru"));
    }

    @Test
    public void wrongTokenTest() {
        String token = tokenHandler.generateAccessToken("user@user.ru", LocalDateTime.now().plusDays(14));
        String extractId = tokenHandler.extractId(token).get();

        assertFalse(extractId.equals("user1@user.ru"));
    }
}