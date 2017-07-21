package repository;

import mongoEntities.ClientRequest;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ClientRequestRepository extends MongoRepository<ClientRequest, ObjectId> {

    @Query("{clientEmail: ?0}")
    ClientRequest findLastClientRequest(String email, Sort sort);

    @Query("{animalId: ?0}, $limit:1")
    ClientRequest findByAnimalId(Integer animalId);
}
