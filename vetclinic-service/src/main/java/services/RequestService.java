package services;

import entities.issue.Issue;
import entities.issue.IssueInfo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import dao.IssueDao;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class RequestService {

    @Autowired
    private IssueDao issueDao;

    @Autowired
    private MongoTemplate mongoTemplate;


    public IssueInfo findLastClientRequest(String email) {

//        GroupOperation groupOpt = group("_id").push("history").as("history");
//        MatchOperation matchOpt = match(new Criteria("clientEmail").is(email));
//        SortOperation sortOpt = sort(new Sort(Sort.Direction.DESC, "history.requestDate"));
//        UnwindOperation unwind = Aggregation.unwind("history");

        GroupOperation groupOpt = group("history");
        MatchOperation matchOpt = match(new Criteria("clientEmail").is(email));
        SortOperation sortOpt = sort(new Sort(Sort.Direction.DESC, "_id.requestDate"));
        UnwindOperation unwind = Aggregation.unwind("history");

        Aggregation aggregation = Aggregation.newAggregation(matchOpt, unwind, groupOpt, sortOpt);

        AggregationResults<IssueInfo> aggregate = mongoTemplate.aggregate(aggregation, "request", IssueInfo.class);

        IssueInfo info = aggregate.getMappedResults().get(0);

        return info;
    }

    public Issue findById(ObjectId id) {
        return issueDao.findOne(id);
    }

    public Integer requstCount(Integer animalId) {
        return null;
    }

    public List<Issue> findAll(Integer animalId) {
        return null;
    }

}
