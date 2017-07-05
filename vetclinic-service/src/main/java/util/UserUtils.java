package util;

import enums.UserType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public final class UserUtils {

    public static UserType defineUserType(String email){
        String[] partsEmails = email.split("@");
        if (partsEmails.length < 2){
            throw new UsernameNotFoundException(email);
        }
        return partsEmails[1].equals("vetclinic.ru") ? UserType.EMPLOYEE : UserType.USER;
    }
}
