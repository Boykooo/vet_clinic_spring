package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by andrey on 08.06.17.
 */

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "reg_date", nullable = false)
    private Date regDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_email", nullable = false)
    @JsonIgnoreProperties("animals")
    private Client client;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "animal")
    @JsonIgnoreProperties("animal")
    private Patient patient;

    public Animal(String name, Integer age, String description,
                  Date regDate, Client client, Patient patient) {
        this.name = name;
        this.age = age;
        this.description = description;
        this.regDate = regDate;
        this.client = client;
        this.patient = patient;
    }

    public Animal() {

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

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    //endregion


}
