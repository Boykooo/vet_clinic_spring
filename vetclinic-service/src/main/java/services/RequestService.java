package services;

import mongoEntities.ClientRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.ClientRequestRepository;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private ClientRequestRepository clientRequestRepository;

    public ClientRequest findLastClientRequest(String email) {
        return clientRequestRepository.findLastClientRequest(
                email,
                new Sort(Sort.Direction.DESC, "history")
        );
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
