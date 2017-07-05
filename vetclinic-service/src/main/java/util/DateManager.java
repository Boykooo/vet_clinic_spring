package util;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

public class DateManager {

    private static Integer expires = 365; // one year

    public static LocalDateTime getDateForToken() {
        return LocalDateTime.now().plusDays(365);
    }
}
