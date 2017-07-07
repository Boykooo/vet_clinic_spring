package repository;

import entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andrey on 07.06.17.
 */
public interface ClientRepository extends JpaRepository<Client, String> {
}
