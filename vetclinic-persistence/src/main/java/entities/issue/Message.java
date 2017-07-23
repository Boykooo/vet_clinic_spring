package entities.issue;


import java.util.Date;

public class Message {
    private String email;
    private String message;
    private Date date;

    public Message(String email, String message, Date date) {
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

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    //endregion
}
