package mongoEntities;


import java.util.Date;
import java.util.List;

public class RequestInfo {

    private String header;
    private String description;
    private String employeeEmail;
    private Date requestDate;

    private List<Message> messages;

    public RequestInfo(String header, String description, String employeeEmail,
                       Date requestDate, List<Message> messages) {
        this.header = header;
        this.description = description;
        this.employeeEmail = employeeEmail;
        this.requestDate = requestDate;
        this.messages = messages;
    }

    public RequestInfo() {

    }


    //region GetSet

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //endregion
}
