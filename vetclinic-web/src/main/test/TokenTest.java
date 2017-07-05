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

        String employeeToken = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJlbXBsb3llZUB2ZXRjbGluaWMucnUiLCJleHAiOjE1MzA4MTE3OTN9.IJ2I1W1tECQWpR4v4KlmUOTCX05cHWQ1AikdgmdTWge0ghAmhSXwcSlFy1faytnnBcOlABsdf6NBd2oQaiBS1g";
        Optional<String> email = handler.extractId(employeeToken);

        System.out.println(email.orElse("null"));
    }
}