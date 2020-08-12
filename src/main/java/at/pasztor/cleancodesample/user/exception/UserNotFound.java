package at.pasztor.cleancodesample.user.exception;

import at.pasztor.cleancodesample.common.entity.ErrorCode;
import at.pasztor.cleancodesample.common.exception.ApiException;
import org.springframework.http.HttpStatus;

public class UserNotFound extends ApiException {
    public UserNotFound() {
        super(HttpStatus.NOT_FOUND, ErrorCode.USER_NOT_FOUND, "A user was not found.");
    }
}
