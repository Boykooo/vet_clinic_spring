package services;

import dao.IssueDao;
import entities.issue.Issue;
import entities.issue.IssueInfo;
import entities.issue.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import util.DateManager;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

@Service
public class IssueService {

    @Autowired
    private IssueDao issueDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void addMessage(Long issueInfoId, Message message) {

        Issue foundIssue = issueDao.findByIssueId(issueInfoId);

        if (foundIssue != null) {
            message.setDate(DateManager.getCurrentDate());
            List<IssueInfo> history = foundIssue.getHistory();

            history.stream()
                    .filter(info -> Objects.equals(info.getIssueId(), issueInfoId))
                    .findFirst()
                    .ifPresent(
                            x -> x.addMessage(message)
                    );

            foundIssue.setHistory(history);

            issueDao.save(foundIssue);
        }
    }

    public IssueInfo getIssueInfoById(Long issueInfoId) {
        Issue foundIssue = issueDao.findByIssueId(issueInfoId);
        IssueInfo issueInfo = null;

        if (foundIssue != null) {
            issueInfo = foundIssue.getHistory().stream()
                    .filter(info -> Objects.equals(info.getIssueId(), issueInfoId))
                    .findFirst()
                    .get();
        }
        return issueInfo;
    }

    public List<IssueInfo> findIssueInfoByAnimalid(Integer id) {

        GroupOperation groupOpt = group("history");
        MatchOperation matchOpt = match(new Criteria("animalId").is(id));
        SortOperation sortOpt = sort(new Sort(Sort.Direction.DESC, "_id.requestDate"));
        UnwindOperation unwind = Aggregation.unwind("history");

        Aggregation aggregation = Aggregation.newAggregation(matchOpt, unwind, groupOpt, sortOpt);

        AggregationResults<IssueInfo> aggregate = mongoTemplate.aggregate(aggregation, "request", IssueInfo.class);

        return aggregate.getMappedResults();
    }

    public List<IssueInfo> findIssueByEmail(String email) {

        UnwindOperation historyUnwind = Aggregation.unwind("history");
        GroupOperation historyGroup = group("history.messages");
        MatchOperation matchOpt = match(new Criteria("_id.email").is(email));
        UnwindOperation idUnwind = Aggregation.unwind("_id");
        GroupOperation idGroup = group("_id");
        SortOperation sortOpt = sort(new Sort(Sort.Direction.DESC, "_id.date"));

        Aggregation aggregation = Aggregation.newAggregation(
                historyUnwind,
                historyGroup,
                matchOpt,
                idUnwind,
                idGroup,
                sortOpt
        );

        AggregationResults<IssueInfo> aggregate = mongoTemplate.aggregate(aggregation, "request", IssueInfo.class);

        return aggregate.getMappedResults();
    }

    public IssueInfo findLastChangedByEmail(String email) {

        UnwindOperation historyUnwind = Aggregation.unwind("history");
        GroupOperation historyGroup = group("history.messages");
        UnwindOperation idUnwind = Aggregation.unwind("_id");
        GroupOperation idGroup = group("_id");
        MatchOperation matchOpt = match(new Criteria("_id.email").is(email));
        SortOperation sortOpt = sort(new Sort(Sort.Direction.DESC, "_id.date"));
        LimitOperation limitOperation = new LimitOperation(1);

        Aggregation aggregation = Aggregation.newAggregation(
                historyUnwind,
                historyGroup,
                idUnwind,
                matchOpt,
                sortOpt,
                limitOperation
        );

        AggregationResults<Message> aggregate = mongoTemplate.aggregate(aggregation, "request", Message.class);
        Message message = aggregate.getUniqueMappedResult();

        Issue one = issueDao.findOne(Example.of(
                new Issue(
                        new IssueInfo(message)
                )
        ));

        return null;
    }


    public List<IssueInfo> findByIdAndEmail(Integer animalId, String email){

        MatchOperation idMatch = match(new Criteria("animalId").is(animalId));
        UnwindOperation historyUnwind = Aggregation.unwind("history");
        GroupOperation historyGroup = group("history");
        MatchOperation emailMatch = match(new Criteria("_id.employeeEmail").is(email));
        SortOperation sortOpt = sort(new Sort(Sort.Direction.DESC, "_id.requestDate"));

        Aggregation aggregation = Aggregation.newAggregation(
                idMatch,
                historyUnwind,
                historyGroup,
                emailMatch,
                sortOpt
        );

        AggregationResults<IssueInfo> aggregate = mongoTemplate.aggregate(aggregation, "request", IssueInfo.class);

        return aggregate.getMappedResults();
    }
}
