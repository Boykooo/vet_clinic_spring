package dto;

import entities.User;

import java.util.List;

/**
 * Created by andrey on 07.06.17.
 */
public class UserDto {

    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String role;
    private List<PatientDto> patients;

    public UserDto(String email, String password, String phoneNumber,
                   String firstName, String lastName, List<PatientDto> patients) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = "user";
        this.patients = patients;
    }

    public UserDto(){
        this.role = "user";
    }

    //region GetSet

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public List<PatientDto> getPatients() {
        return patients;
    }
    public void setPatients(List<PatientDto> patients) {
        this.patients = patients;
    }

    //endregion
}
