package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;


public class AnimalDto {

    private Integer id;
    private String name;
    private Integer age;
    private String description;
    private Date regDate;
    @JsonIgnoreProperties("animals")
    private ClientDto client;
    @JsonIgnoreProperties(value = "animal", allowSetters = true, allowGetters = true)
    private PatientDto patient;
    private boolean ill;

    public AnimalDto(String name, Integer age, String description,
                     Date regDate, ClientDto client, PatientDto patient) {
        this.name = name;
        this.age = age;
        this.description = description;
        this.regDate = regDate;
        this.client = client;
        this.patient = patient;
    }

    public AnimalDto(String name, Integer age, String description, ClientDto client) {
        this.name = name;
        this.age = age;
        this.client = client;
        this.description = description;
    }

    public AnimalDto(Integer id, String name, Integer age, String description, ClientDto client) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.client = client;
        this.description = description;
    }

    public AnimalDto() {

    }

    //region GetSet

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIll() {
        return ill;
    }
    public void setIll(boolean ill) {
        this.ill = ill;
    }

    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public ClientDto getClient() {
        return client;
    }
    public void setClient(ClientDto client) {
        this.client = client;
    }

    public PatientDto getPatient() {
        return patient;
    }
    public void setPatient(PatientDto patient) {
        this.patient = patient;
    }

    //endregion

}
