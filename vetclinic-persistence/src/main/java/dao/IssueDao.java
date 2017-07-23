package dao;

import entities.issue.Issue;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IssueDao extends MongoRepository<Issue, ObjectId> {

    @Query("{animalId: ?0}, $limit:1")
    Issue findByAnimalId(Integer animalId);

    @Query("{history._id: ?0}")
    Issue findByIssueId(Long issueInfoId);
}
