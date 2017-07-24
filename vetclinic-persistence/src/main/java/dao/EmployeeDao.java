package dao;

import entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, String> {

//    @Query("select * from vetclinic.user u LIMIT :start OFFSET :end")
//    List<Employee> getLimit(@Param("start") Integer start, @Param("end") Integer end);
}
