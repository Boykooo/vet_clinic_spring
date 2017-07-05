package app.responses;

public class ErrorResponse extends BaseResponse {

    private ErrorType error;

    public ErrorResponse(ErrorType error) {
        super(ResponseStatus.ERROR);
        this.error = error;
    }

    public ErrorType getError() {
        return error;
    }
    public void setError(ErrorType error) {
        this.error = error;
    }
}
