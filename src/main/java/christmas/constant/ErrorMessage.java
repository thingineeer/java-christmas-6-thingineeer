package christmas.constant;

public enum ErrorMessage {
    PREFIX_STRING("[ERROR] "),
    DATE_OUT_OF_RANGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_VALIDATED_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ORDER_ONLY_BEVERAGE("음료만 입력한 주문 입니다. 다시 입력해 주세요."),
    TOTAL_MENU_COUNT_IS_OVER("메뉴 개수의 합이 " + Constants.MENU_LIMIT + "개가 초과 합니다. 다시 입력해 주세요.");
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX_STRING.message + this.message;
    }


}
