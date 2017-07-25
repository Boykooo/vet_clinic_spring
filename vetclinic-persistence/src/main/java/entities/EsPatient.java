package entities;


import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Document(indexName = "vetclinic", type = "patient")
public class EsPatient {

    @Id
    private String id;
    private Integer patientId;
    private String employeeEmail;
    private String clientEmail;
    private String clientName;

    public EsPatient(String employeeEmail, String clientEmail, String clientName, Integer patientId) {
        this.employeeEmail = employeeEmail;
        this.clientEmail = clientEmail;
        this.clientName = clientName;
        this.patientId = patientId;
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

    public Integer getPatientId() {
        return patientId;
    }
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    //endregion
}
