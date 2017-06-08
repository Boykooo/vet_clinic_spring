package repository;

import entities.Animal;
import entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andrey on 08.06.17.
 */


public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
