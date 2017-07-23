package dao;

import entities.SequenceId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class SequenceDao {

    @Autowired
    private MongoOperations mongoOperations;

    public Long getNextSequenceId(String secuenceName) {

        Query query = new Query(Criteria.where("_id").is(secuenceName));

        Update update = new Update();
        update.inc("seq", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        SequenceId seqId =
                mongoOperations.findAndModify(query, update, options, SequenceId.class);

        return seqId.getSeq();
    }


}
