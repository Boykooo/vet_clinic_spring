package repository;

import mongoEntities.Request;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RequestRepository extends MongoRepository<Request, Integer> {

    @Query("{clientEmail: ?0}")
    Request findLastClientRequest(String email, Sort sort);
}
