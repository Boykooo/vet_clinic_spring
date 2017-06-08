package repository;

import entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andrey on 07.06.17.
 */
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
