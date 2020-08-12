package at.pasztor.cleancodesample.common.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import at.pasztor.cleancodesample.common.entity.ErrorCode;
import at.pasztor.cleancodesample.common.exception.ApiException;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.Map;

public class InvalidParameters extends ApiException {
    @SuppressWarnings("WeakerAccess")
    @JsonProperty("fieldErrors")
    public final Map<String, Collection<at.pasztor.cleancodesample.common.validation.Error>> fieldErrors;

    InvalidParameters(
        Map<String, Collection<at.pasztor.cleancodesample.common.validation.Error>> fieldErrors
    ) {
        super(
            HttpStatus.BAD_REQUEST,
            ErrorCode.INVALID_PARAMETERS,
            "One or more request parameters are invalid. Please check the 'fieldErrors' field for details."
        );
        this.fieldErrors = fieldErrors;
    }
}
