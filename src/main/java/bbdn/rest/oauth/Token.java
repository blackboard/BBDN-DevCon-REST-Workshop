package bbdn.rest.oauth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {

	@JsonProperty("access_token")
    private String access_token;
	@JsonProperty("token_type")
	private String token_type;
	@JsonProperty("expires_in")
    private String expires_in;

    public Token() {
    }

    public String getToken() {
        return access_token;
    }

    public void setToken(String access_token) {
        this.access_token = access_token;
    }

    public String getType() {
        return token_type;
    }

    public void setType(String token_type) {
        this.token_type = token_type;
    }

    public String getExpiry() {
        return expires_in;
    }

    public void setExpiry(String expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "Token{" +
                "access_token=" + access_token + '\'' +
                ", token_type=" + token_type + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }
}
