package repository;

import entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by andrey on 07.06.17.
 */
public interface EmployeeRepository extends JpaRepository<Employee, String> {

//    @Query("select * from vetclinic.user u LIMIT :start OFFSET :end")
//    List<Employee> getLimit(@Param("start") Integer start, @Param("end") Integer end);
}
