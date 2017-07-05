package util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class CryptManager {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String getCryptPassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static boolean matchesPasswords(String password, String encodePassword){
        return bCryptPasswordEncoder.matches(password, encodePassword);
    }
}
