package util;

import enums.UserType;

public final class UserUtils {

    public static UserType defineUserType(String email){
        String[] partsEmails = email.split("@");
        return partsEmails[1].equals("vetclinic.ru") ? UserType.EMPLOYEE : UserType.USER;
    }
}
