package dao;

import entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnimalDao extends JpaRepository<Animal, Integer> {

    @Query("select a from Animal a where a.client_email = :#{#email}")
    List<Animal> findByClientEmail(@Param("email") String email);

    @Query("select a from Animal a where a.client_email = :#{#email}")
    Page<Animal> findByClientEmail(@Param("email") String email, Pageable pageable);

    Long countByClientEmail(@Param("email") String email);

}
