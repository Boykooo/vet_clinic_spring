package services;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class AnimalImageService {

    @Autowired
    private GridFsTemplate gridFsTemplate;
    private final String idFieldName = "animal_id";

    public InputStream findById(Integer animalId) {
        GridFSDBFile image = gridFsTemplate.findOne(new Query(Criteria.where("metadata." + idFieldName).is(animalId)));

        return image != null ? image.getInputStream() : null;
    }

    public void add(InputStream inputStream, Integer animalId) throws ObjectAlreadyExistException {
        DBObject metaData = new BasicDBObject();
        metaData.put(idFieldName, animalId);
        gridFsTemplate.store(inputStream, metaData);
    }

    public void update(InputStream inputStream, Integer animalId) throws ObjectAlreadyExistException {
        delete(animalId);
        add(inputStream, animalId);
    }

    public void delete(Integer animalId) {
        gridFsTemplate.delete(new Query(Criteria.where("metadata." + idFieldName).is(animalId)));
    }

}
