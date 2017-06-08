package repository;

import entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andrey on 07.06.17.
 */
public interface UserRepository extends JpaRepository<User, String> {
}
