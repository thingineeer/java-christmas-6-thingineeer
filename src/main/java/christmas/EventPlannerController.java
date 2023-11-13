package christmas;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.OrderRepository;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventPlannerController {
    private final InputView inputView;
    private final OrderRepository orderRepository;
    private Date date;

    public EventPlannerController(InputView inputView, OrderRepository orderRepository) {
        this.inputView = inputView;
        this.orderRepository = orderRepository;
    }

    public void run() {
        initialize();

    }

    private void initialize() {
        int dateInput = inputView.readDate();
        date = new Date(dateInput);
        Map<String, Integer> menuQuantityMap = inputView.readMenuAndQuantityForOrder();

        menuQuantityMap.entrySet().stream()
                .map(menu -> new Order(menu.getKey(), menu.getValue()))
                .forEach(orderRepository::addOrder);
        OutputView.printBENEFIT(date.getDate());
    }
}

