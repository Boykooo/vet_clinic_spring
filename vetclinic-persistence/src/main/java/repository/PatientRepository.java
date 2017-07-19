package repository;

import entities.Employee;
import entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("select p from Patient p where status = 'NEW' ")
    List<Patient> findNew();

    @Query("select p from Patient p where status = 'IN_PROGRESS' and p.employee.email = :email")
    List<Patient> findInProgress(@Param("email") String email);

}
