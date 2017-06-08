package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entities.Patient;
import entities.User;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by andrey on 08.06.17.
 */
public class AnimalDto {

    private Integer id;
    private String name;
    private Integer age;
    private String description;
    private Date regDate;
    @JsonIgnoreProperties("animals")
    private UserDto user;
    @JsonIgnoreProperties("animal")
    private PatientDto patient;

    public AnimalDto(String name, Integer age, String description,
                  Date regDate, UserDto user, PatientDto patient) {
        this.name = name;
        this.age = age;
        this.description = description;
        this.regDate = regDate;
        this.user = user;
        this.patient = patient;
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

    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public UserDto getUser() {
        return user;
    }
    public void setUser(UserDto user) {
        this.user = user;
    }

    public PatientDto getPatient() {
        return patient;
    }
    public void setPatient(PatientDto patient) {
        this.patient = patient;
    }

    //endregion


}
