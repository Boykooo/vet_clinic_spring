package entities;

import enums.Role;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by andrey on 07.06.17.
 */

@Entity
@Table(name = "client")
public class Client implements BaseUser {

    @Id
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "phone_number", length = 100, nullable = false)
    private String phoneNumber;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "reg_date", nullable = false)
    private Date regDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<Animal> animals;

    public Client(String email, String password, String phoneNumber, String firstName,
                  String lastName, Date regDate) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.regDate = regDate;
    }

    public Client(String email) {
        this.email = email;
    }

    public Client() {
    }

    @Override
    public Role getRole() {
        return Role.CLIENT;
    }

    //region GetSet

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
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
