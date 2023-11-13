package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    List<Order> orderRepository = new ArrayList<>();

    public void addOrder(Order order) {
        orderRepository.add(order);
    }

    public int size(Order order) {
        return orderRepository.size();
    }
}
