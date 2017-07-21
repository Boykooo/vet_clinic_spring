package repository;

import mongoEntities.ClientRequest;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ClientRequestRepository extends MongoRepository<ClientRequest, ObjectId> {

    @Query("{$aggregate: ?0}")
    ClientRequest findLastClientRequest(Aggregation aggregation);

    @Query("{animalId: ?0}, $limit:1")
    ClientRequest findByAnimalId(Integer animalId);
}
