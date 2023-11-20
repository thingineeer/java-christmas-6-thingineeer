package christmas;

import christmas.controller.EventPlannerController;
import christmas.domain.OrderRepository;
import christmas.view.InputView;
import christmas.view.OutputView;


public class Application {
    public static void main(String[] args) {
        try {
            EventPlannerController eventPlannerController = new EventPlannerController(new InputView(), new OrderRepository());
            eventPlannerController.run();
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
        }
    }
}
