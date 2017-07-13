package app.responses;

public class DataResponse<Data> extends BaseResponse {

    private Data data;

    public DataResponse(Data data) {
        super(ResponseStatus.OK);
        this.data = data;
    }

    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }

}
