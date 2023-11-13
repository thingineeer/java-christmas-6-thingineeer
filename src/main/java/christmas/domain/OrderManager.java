package christmas.domain;

import christmas.view.OutputView;

public class OrderManager {
    // 주문 정보를 관리하는 클래스로, 각 메뉴와 수량을 관리합니다.
    private OrderRepository orders;

    public OrderManager(OrderRepository orders) {
        this.orders = orders;
    }

    public void printAllDetail() {
        OutputView.printOrderMenu();
        orders.getOrderList().stream()
                .map(Order::getEachOrder)
                .forEach(OutputView::printMessage);
    }
}
