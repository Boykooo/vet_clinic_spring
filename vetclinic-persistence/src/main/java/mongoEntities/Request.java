package mongoEntities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.util.List;

@Document(collection = "request")
public class Request {

    private String clientEmail;
    private String employeeEmail;
    private Date requestDate;
    private List<Message> messages;

    public Request(String clientEmail, String employeeEmail, Date requestDate, List<Message> messages) {
        this.clientEmail = clientEmail;
        this.employeeEmail = employeeEmail;
        this.requestDate = requestDate;
        this.messages = messages;
    }

    public Request() {
    }

    //region GetSet

    public String getClientEmail() {
        return clientEmail;
    }
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }
    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Date getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public List<Message> getMessages() {
        return messages;
    }
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    //endregion
}
