package mongoEntities;


import java.sql.Date;

public class Message {
    private String email;
    private String msg;
    private Date date;

    public Message(String msg, Date date, String email) {
        this.msg = msg;
        this.date = date;
        this.email = email;
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

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    //endregion

}
