package at.pasztor.cleancodesample.user.exception;

import at.pasztor.cleancodesample.common.entity.ErrorCode;
import at.pasztor.cleancodesample.common.exception.ApiException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExists extends ApiException {
    public UserAlreadyExists() {
        super(HttpStatus.CONFLICT, ErrorCode.USER_ALREADY_EXISTS, "A user with the specified details already exists.");
    }
}
