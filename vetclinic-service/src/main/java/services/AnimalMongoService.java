package services;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class AnimalMongoService {

    @Autowired
    private GridFsTemplate gridFsTemplate;
    private final String idFieldName = "animal_id";

    public List<InputStream> findAll() {
        return null;
    }

    public InputStream findById(Integer animalId) {
        return gridFsTemplate.findOne(new Query(Criteria.where("metadata." + idFieldName).is(animalId))).getInputStream();
    }

    public void add(InputStream inputStream, Integer animalId) throws ObjectAlreadyExistException {
        DBObject metaData = new BasicDBObject();
        metaData.put(idFieldName, animalId);
        gridFsTemplate.store(inputStream, metaData);
    }

    public void update(InputStream inputStream, Integer animalId) throws ObjectNotFoundException {
//        gridFsTemplate
    }

    public void delete(Integer animalId) {
        gridFsTemplate.delete(new Query(Criteria.where(idFieldName).is(animalId)));
    }

}
