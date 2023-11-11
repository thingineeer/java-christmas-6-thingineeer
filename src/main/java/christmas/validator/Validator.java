package christmas.validator;

public interface Validator {
    void check(final String input) throws IllegalArgumentException;
}
