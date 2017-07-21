package mongoEntities;

import java.sql.Date;

public class Message {
    private String email;
    private String message;
    private String date;

    public Message(String email, String message, String date) {
        this.email = email;
        this.message = message;
        this.date = date;
    }

    public Message() {
    }

    //region GetSet

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    //endregion
}
