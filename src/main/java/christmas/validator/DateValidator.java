package christmas.validator;

import christmas.constant.ErrorMessage;

public class DateValidator implements Validator{
    @Override
    public void check(String input) {
        checkInteger(input);
        checkOutOfRange(input);
    }

    private void checkInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.DATE_OUT_OF_RANGE.getMessage());
        }
    }
    private void checkOutOfRange(String input) {
        int date = Integer.parseInt(input);
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(ErrorMessage.DATE_OUT_OF_RANGE.getMessage());
        }
    }
}
