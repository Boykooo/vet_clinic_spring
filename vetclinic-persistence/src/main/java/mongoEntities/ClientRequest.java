package mongoEntities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "request")
public class ClientRequest {

    @Id
    private ObjectId id;
    private Integer animalId;
    private String clientEmail;

    private List<RequestInfo> history;

    public ClientRequest(ObjectId id, Integer animalId, String clientEmail, List<RequestInfo> history) {
        this.id = id;
        this.animalId = animalId;
        this.clientEmail = clientEmail;
        this.history = history;
    }

    public ClientRequest(Integer animalId, String clientEmail, RequestInfo info) {
        this.animalId = animalId;
        this.clientEmail = clientEmail;
        this.history = new ArrayList<>();
        this.history.add(info);
    }

    public ClientRequest(Integer animalId) {
        this.animalId = animalId;
    }

    public ClientRequest() {

    }

    public void addRequestInfo(RequestInfo info) {
        this.history.add(info);
    }


    //region GetSet

    public Integer getAnimalId() {
        return animalId;
    }
    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }

    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getClientEmail() {
        return clientEmail;
    }
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public List<RequestInfo> getHistory() {
        return history;
    }
    public void setHistory(List<RequestInfo> history) {
        this.history = history;
    }

    //endregion
}
