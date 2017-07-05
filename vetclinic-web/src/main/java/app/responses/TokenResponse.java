package app.responses;

public class TokenResponse extends BaseResponse {

    private String token;

    public TokenResponse(String token) {
        super(ResponseStatus.OK);
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
