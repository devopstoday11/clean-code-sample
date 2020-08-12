package at.pasztor.cleancodesample.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

@ApiModel
public class UserEmailVerification {
    @JsonProperty(value = "userId", required = true)
    public final String userId;
    @JsonIgnore
    public final String code;
    @JsonProperty(value = "email", required = true)
    public final String email;

    public UserEmailVerification(
        final String userId,
        final String code,
        final String email
    ) {
        this.userId = userId;
        this.code = code;
        this.email = email;
    }
}
