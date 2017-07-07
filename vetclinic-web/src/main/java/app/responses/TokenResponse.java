package app.responses;

import enums.UserType;

public class TokenResponse extends BaseResponse {

    private String token;
    private UserType userType;

    public TokenResponse(String token, UserType userType) {
        super(ResponseStatus.OK);
        this.token = token;
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
