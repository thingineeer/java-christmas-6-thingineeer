package christmas;

public class ErrorMessage {

    private String PREFIX = "[ERROR] ";
    public static final String NOT_NUMERIC = "숫자가 아닌 값을 입력 했습니다.";

    public static final String DATE_OUT_OF_RANGE =
            "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String NOT_VALIDATED_ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private String message;

    private ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + this.message;
    }


}
