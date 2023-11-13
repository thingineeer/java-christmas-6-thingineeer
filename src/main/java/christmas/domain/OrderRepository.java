package christmas.domain;

import static christmas.constant.Menu.ALL_MENU_NAME;

import christmas.constant.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderRepository {
    List<Order> orderRepository = new ArrayList<>();

    public void addOrder(Order order) {
        orderRepository.add(order);
    }

    public int size(Order order) {
        return orderRepository.size();
    }

    public List<Order> getOrderList() {
        return orderRepository;
    }

    public int calculateTotalAmount() {
        return orderRepository.stream()
                .mapToInt(order -> {
                    Menu menu = Menu.getMenuName(order.retrieveMenuName());
                    return menu.getPrice() * order.retrieveMenuQuantity();
                })
                .sum();
    }
}
