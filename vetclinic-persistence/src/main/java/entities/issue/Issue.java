package entities.issue;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "request")
public class Issue {

    @Id
    private ObjectId id;
    private Integer animalId;
    private String clientEmail;

    private List<IssueInfo> history;

    public Issue(ObjectId id, Integer animalId, String clientEmail, List<IssueInfo> history) {
        this.id = id;
        this.animalId = animalId;
        this.clientEmail = clientEmail;
        this.history = history;
    }

    public Issue(Integer animalId, String clientEmail, IssueInfo info) {
        this.animalId = animalId;
        this.clientEmail = clientEmail;
        this.history = new ArrayList<>();
        this.history.add(info);
    }

    public Issue(Integer animalId) {
        this.animalId = animalId;
    }

    public Issue() {

    }

    public void addRequestInfo(IssueInfo info) {
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

    public List<IssueInfo> getHistory() {
        return history;
    }
    public void setHistory(List<IssueInfo> history) {
        this.history = history;
    }

    //endregion
}
