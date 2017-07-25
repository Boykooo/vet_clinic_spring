package security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component("tokenHandler")
public class TokenHandler {

    private final String secretKey;

    public TokenHandler() {
        this.secretKey = "secretKey";
//        String jwtKey = "jwtKey1234567890";
//        byte[] decodeKey = BaseEncoding.base64().decode(jwtKey);
//        secretKey = new SecretKeySpec(decodeKey, 0, decodeKey.length, "AES");
    }

    public Optional<String> extractId(String token) {
        if (token.isEmpty()){
            return Optional.empty();
        }

        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims body = claimsJws.getBody();

            return Optional
                    .ofNullable(body.getId())
                    .map(String::new);

        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    public String generateAccessToken(String email, LocalDateTime expires){
        return Jwts.builder()
                .setId(email)
                .setExpiration(Date.from(expires.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}
