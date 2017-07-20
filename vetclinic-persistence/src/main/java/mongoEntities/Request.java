package mongoEntities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.util.List;

@Document(collection = "request")
public class Request {

    private ObjectId id;
    private Integer animalId;
    private String clientEmail;

    private List<RequestInfo> history;

    public Request(Integer animalId, String clientEmail, List<RequestInfo> history) {
        this.animalId = animalId;
        this.clientEmail = clientEmail;
        this.history = history;
    }

    public Request() {
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
