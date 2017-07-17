package repository;

import entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("select p from Patient p where status = 'NEW' ")
    List<Patient> findNew();

}
