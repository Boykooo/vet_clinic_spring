package entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by andrey on 07.06.17.
 */

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "animal_name", length = 100, nullable = false)
    private String animalName;

    @Column(name = "animal_age", length = 100, nullable = false)
    private String animalAge;

    @Column(name = "status", length = 100, nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_email")
    private User employee;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private String endDate;

    public Patient(String animalName, String animalAge, String status,
                   User user, String description, Date startDate, String endDate) {
        this.animalName = animalName;
        this.animalAge = animalAge;
        this.status = status;
        this.user = user;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Patient() {

    }

}
