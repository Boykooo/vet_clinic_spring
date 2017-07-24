package entities;


import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "vetclinic", type = "patient")
public class EsPatient {

    private String id;
    private String employeeEmail;
    private String clientEmail;

    public EsPatient(String employeeEmail, String clientEmail) {
        this.id = id;
        this.employeeEmail = employeeEmail;
        this.clientEmail = clientEmail;
    }

    public EsPatient() {

    }

    //region GetSet

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
