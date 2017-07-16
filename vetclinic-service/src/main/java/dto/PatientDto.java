package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.PatientStatus;

import java.sql.Date;

public class PatientDto {

    private Integer id;
    private PatientStatus status;
    private AnimalDto animal;
    @JsonIgnoreProperties(value = "patients", allowSetters = true)
    private EmployeeDto employee;
    private String description;
    private Date startDate;
    private Date endDate;

    public PatientDto(AnimalDto animal, String description, PatientStatus status,
                      EmployeeDto employee, Date startDate) {
        this.animal = animal;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.employee = employee;
    }

    public PatientDto() {

    }

    //region GetSet

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PatientStatus getStatus() {
        return status;
    }

    public void setStatus(PatientStatus status) {
        this.status = status;
    }

    public AnimalDto getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalDto animal) {
        this.animal = animal;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    //endregion
}
