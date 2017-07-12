package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.PatientStatus;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PatientStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_email")
    @JsonIgnoreProperties("patients")
    private Employee employee;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    public Patient(Animal animal, String description, PatientStatus status,
                   Employee employee, Date startDate) {
        this.animal = animal;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.employee = employee;
    }

    public Patient() {

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

    public Animal getAnimal() {
        return animal;
    }
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
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
