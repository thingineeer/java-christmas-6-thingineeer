package christmas.domain;

import static christmas.constant.Menu.ALL_MENU_NAME;

import christmas.constant.Menu;
import christmas.util.Utils;
import christmas.view.OutputView;

public class OrderPrice {
    // 금액과 관련된 클래스입니다.
    // 할인 전 총 주문금액, 할인 후 예상 결제 금액 등을 관리하는 클래스 입니다.
    private OrderRepository orders;
    private int totalAmount;

    public OrderPrice(OrderRepository orders) {
        this.orders = orders;
    }

    // 할인 전 총 주문 금액 출력 기능
    public void printBeforeDiscountInfo() {
        this.totalAmount = orders.calculateTotalAmount();

        OutputView.printBeforeDiscount();
        String result = Utils.makeFormattedNumberWithComma(this.totalAmount);
        OutputView.printMessage(result + "원");
    }

    public int getTotalAmount() {
        return totalAmount;
    }
}
