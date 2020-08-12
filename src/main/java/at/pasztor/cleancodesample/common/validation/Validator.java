package at.pasztor.cleancodesample.common.validation;

public interface Validator {
    String getUniqueKey();
    String getDescription();
    boolean isValid(Object value);
}
