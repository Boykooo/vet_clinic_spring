import org.junit.Test;
import security.TokenHandler;

import java.time.LocalDateTime;
import java.util.Optional;

public class TokenTest {

    @Test
    public void tokenTest(){
        TokenHandler handler = new TokenHandler();
        String token = handler.generateAccessToken("user@user.ru", LocalDateTime.now().plusDays(14));

        System.out.println(token);

        Optional<String> email = handler.extractId(token);

        System.out.println(email.orElse("null"));
    }
}

