package entities.issue;

import enums.RequestStatus;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

public class IssueInfo {

    private Long id;
    private String header;
    private String description;
    private String employeeEmail;
    private RequestStatus status;
    private Date requestDate;
    private List<Message> messages;



    public IssueInfo(Long id, String header, String description, String employeeEmail,
                     Date requestDate, List<Message> messages, RequestStatus status) {
        this.header = header;
        this.description = description;
        this.employeeEmail = employeeEmail;
        this.requestDate = requestDate;
        this.messages = messages;
        this.status = status;
        this.id = id;
    }

    public IssueInfo() {

    }

    //region GetSet

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

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
