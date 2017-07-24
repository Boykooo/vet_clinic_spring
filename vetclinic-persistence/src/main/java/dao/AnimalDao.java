package dao;

import entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by andrey on 08.06.17.
 */

@Repository
public interface AnimalDao extends JpaRepository<Animal, Integer> {

    @Query("select a from Animal a where a.client_email = :#{#email}")
    List<Animal> findByClientEmail(@Param("email") String email);

}
