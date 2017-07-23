package entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "sequence")
public class SequenceId {

    @Id
    private String id;
    private Long seq;

    public SequenceId(String id, Long seq) {
        this.id = id;
        this.seq = seq;
    }

    public SequenceId() {
    }

    //region GetSet

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Long getSeq() {
        return seq;
    }
    public void setSeq(Long seq) {
        this.seq = seq;
    }

    //endregion

}
