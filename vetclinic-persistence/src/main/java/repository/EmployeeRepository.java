package repository;

import entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andrey on 07.06.17.
 */
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
