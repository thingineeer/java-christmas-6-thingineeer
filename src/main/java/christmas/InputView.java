package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.validator.DateValidator;
import christmas.validator.OrderValidator;
import java.util.Map;

public final class InputView {
    public static final String READ_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String READ_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static DateValidator dateValidator = new DateValidator();
    public static OrderValidator orderValidator = new OrderValidator();
    public int readDate() {
        OutputView.printMessage(READ_DATE_MESSAGE);
        String date = Console.readLine();
        dateValidator.check(date);
        return Integer.parseInt(date);
    }

    public Map<String, Integer> readMenuAndQuantityForOrder() {
        OutputView.printMessage(READ_ORDER_MESSAGE);
        String orderMenuAndQuantity = Console.readLine();
        orderValidator.check(orderMenuAndQuantity);
        Map<String, Integer> Menu = Utils.makeStringToHashMap(orderMenuAndQuantity);
        return Menu;
    }


}
