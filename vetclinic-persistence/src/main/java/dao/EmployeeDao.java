package dao;

import entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andrey on 07.06.17.
 */
public interface EmployeeDao extends JpaRepository<Employee, String> {

//    @Query("select * from vetclinic.user u LIMIT :start OFFSET :end")
//    List<Employee> getLimit(@Param("start") Integer start, @Param("end") Integer end);
}
