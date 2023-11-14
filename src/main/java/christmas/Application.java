package christmas;

import christmas.controller.EventPlannerController;
import christmas.domain.OrderRepository;
import christmas.view.InputView;


public class Application {

    public static void main(String[] args) {
        EventPlannerController eventPlannerController = new EventPlannerController(new InputView(), new OrderRepository());
        eventPlannerController.run();
    }
}
