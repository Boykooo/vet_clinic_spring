package dao;

import entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDao extends JpaRepository<Patient, Integer> {

    @Query("select p from Patient p where status = 'NEW' ")
    List<Patient> findNew();

    @Query("select p from Patient p where status = 'IN_PROGRESS' and p.employee.email = :email")
    List<Patient> findInProgress(@Param("email") String email);

    @Query("select COUNT(p) from Patient p where p.employee.email = :email")
    Long getCoundByEmployeeEmail(@Param("email") String email);

    @Query("select p from Patient p where status = 'IN_PROGRESS' and p.employee.email = :email")
    Page<Patient> findInProgressPagable(@Param("email") String email, Pageable pageable);

}
