package dao;

import entities.issue.Issue;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IssueDao extends MongoRepository<Issue, ObjectId> {

    @Query("{history.issueId: ?0}")
    Issue findByIssueId(Long issueInfoId);

    @Query("{animalId: ?0}")
    Issue findByAnimalId(Integer id, Sort sort);
}
