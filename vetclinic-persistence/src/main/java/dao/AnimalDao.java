package dao;

import entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andrey on 08.06.17.
 */


public interface AnimalDao extends JpaRepository<Animal, Integer> {
}
