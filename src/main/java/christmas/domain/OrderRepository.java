package christmas.domain;

import christmas.constant.Menu;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    List<Order> orderRepository = new ArrayList<>();

    public void addOrder(final Order order) {
        orderRepository.add(order);
    }

    public int size(final Order order) {
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
