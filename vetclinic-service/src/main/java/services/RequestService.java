package services;

import mongoEntities.Request;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.RequestRepository;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Request findLastClientRequest(String email) {
        return requestRepository.findLastClientRequest(
                email,
                new Sort(Sort.Direction.DESC, "history")
        );
    }

    public Request findById(ObjectId id) {
        return requestRepository.findOne(id);
    }

    public Integer requstCount(Integer animalId) {
        return null;
    }

    public List<Request> findAll(Integer animalId) {
        return null;
    }


}
