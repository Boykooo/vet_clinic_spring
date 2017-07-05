package app.responses;

public class BaseResponse {
    private ResponseStatus status;

    public BaseResponse(ResponseStatus status) {
        this.status = status;
    }

    public ResponseStatus getStatus() {
        return status;
    }
    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}
