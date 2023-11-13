package christmas.constant;

import christmas.constant.Constants;

public enum ErrorMessage {

    PREFIX_STRING("[ERROR] "),
    NOT_NUMERIC("숫자가 아닌 값을 입력 했습니다."),
    DATE_OUT_OF_RANGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_VALIDATED_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ORDER_ONLY_BEVERAGE("음료만 입력한 주문 입니다. 다시 입력해 주세요."),
    TOTAL_MENU_COUNT_IS_OVER("메뉴 개수의 합이 " + Constants.MENU_LIMIT + "개가 초과 합니다. 다시 입력해 주세요.");
    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX_STRING.message + this.message;
    }


}
