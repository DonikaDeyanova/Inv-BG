package api;

import api.dto.Credentials;

public class LoginAPI extends Request {
    private static final String ENDPOINT = "/login/token";

    public LoginAPI(String token) {
        super(token);
    }

    public String obtainToken(Credentials credentials){
        String body = GSON.toJson(credentials);
        return post(ENDPOINT, body)
                .then().extract()
                .body().jsonPath()
                .getString("token");
    }
}
