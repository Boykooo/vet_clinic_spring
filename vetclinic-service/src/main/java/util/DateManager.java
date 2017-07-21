package util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class DateManager {

    private static Integer expires = 365; // one year

    public static LocalDateTime getDateForToken() {
        return LocalDateTime.now().plusDays(365);
    }

    public static Date getCurrentSqlDate(){
        java.util.Date currDate = new java.util.Date();
        return new Date(currDate.getTime());
    }

    public static String getCurrentFormattedDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return dateFormat.format(new java.util.Date());
    }

    public static java.util.Date getCurrentDate(){
        return new java.util.Date();
    }
}
