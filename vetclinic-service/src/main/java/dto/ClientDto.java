package dto;

import java.sql.Date;
import java.util.List;

/**
 * Created by andrey on 07.06.17.
 */
public class ClientDto {

    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Date regDate;

    private List<AnimalDto> animals;

    public ClientDto(String email, String password, String phoneNumber,
                     String firstName, String lastName, Date regDate) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.regDate = regDate;
    }

    public ClientDto(String email) {
        this.email = email;
    }

    public ClientDto() {
    }

    //region GetSet

    public List<AnimalDto> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalDto> animals) {
        this.animals = animals;
    }

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

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    //endregion
}
