package entities;


import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Document(indexName = "vetclinic", type = "patient")
public class EsPatient {

    @Id
    private String id;
    private String employeeEmail;
    private String clientEmail;
    private String clientName;

    public EsPatient(String employeeEmail, String clientEmail, String clientName) {
        this.employeeEmail = employeeEmail;
        this.clientEmail = clientEmail;
        this.clientName = clientName;
    }

    public EsPatient() {

    }

    //region GetSet

    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }
    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getClientEmail() {
        return clientEmail;
    }
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    //endregion
}
