package services;

import mongoEntities.ClientRequest;
import mongoEntities.RequestInfo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import repository.ClientRequestRepository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class RequestService {

    @Autowired
    private ClientRequestRepository clientRequestRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public RequestInfo findLastClientRequest(String email) {

//        GroupOperation groupOpt = group("_id").push("history").as("history");
//        MatchOperation matchOpt = match(new Criteria("clientEmail").is(email));
//        SortOperation sortOpt = sort(new Sort(Sort.Direction.DESC, "history.requestDate"));
//        UnwindOperation unwind = Aggregation.unwind("history");

        GroupOperation groupOpt = group("history");
        MatchOperation matchOpt = match(new Criteria("clientEmail").is(email));
        SortOperation sortOpt = sort(new Sort(Sort.Direction.ASC, "history.requestDate"));
        UnwindOperation unwind = Aggregation.unwind("history");

        Aggregation aggregation = Aggregation.newAggregation(matchOpt, unwind, sortOpt, groupOpt);

        AggregationResults<RequestInfo> aggregate = mongoTemplate.aggregate(aggregation, "request", RequestInfo.class);

        RequestInfo info = aggregate.getMappedResults().get(0);

        return info;
    }

    public ClientRequest findById(ObjectId id) {
        return clientRequestRepository.findOne(id);
    }

    public Integer requstCount(Integer animalId) {
        return null;
    }

    public List<ClientRequest> findAll(Integer animalId) {
        return null;
    }


}
