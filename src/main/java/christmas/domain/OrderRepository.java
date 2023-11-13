package christmas.domain;

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
}
