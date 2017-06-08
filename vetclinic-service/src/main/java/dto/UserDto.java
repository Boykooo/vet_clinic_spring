package dto;

import entities.Animal;
import entities.User;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private List<AnimalDto> animals;

    public UserDto(String email, String password, String phoneNumber,
                   String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDto() {
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

    //endregion
}
