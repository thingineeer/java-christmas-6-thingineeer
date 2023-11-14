package christmas.controller;

import christmas.domain.BenefitInformation;
import christmas.domain.Date;
import christmas.domain.EventBadge;
import christmas.domain.GiftMenu;
import christmas.domain.Order;
import christmas.domain.OrderManager;
import christmas.domain.OrderPrice;
import christmas.domain.OrderRepository;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class EventPlannerController {
    private final InputView inputView;
    private final OrderRepository orderRepository;
    private Date date;
    private int Amount;

    public EventPlannerController(InputView inputView, OrderRepository orderRepository) {
        this.inputView = inputView;
        this.orderRepository = orderRepository;
    }

    public void run() {
        initialize();

        OrderManager orderInfo = new OrderManager(orderRepository);
        orderInfo.printAllDetail();

        OrderPrice orderPriceInfo = new OrderPrice(orderRepository);
        orderPriceInfo.printBeforeDiscountInfo();
        Amount = orderPriceInfo.getTotalAmount();

        GiftMenu giftMenu = new GiftMenu(Amount);
        giftMenu.printGiftInfo();

        BenefitInformation benefitInformation = new BenefitInformation(date, orderRepository);
        benefitInformation.printDiscountInfo();

        benefitInformation.printTotalDiscount(Amount);
        benefitInformation.printPaymentAmountAfterDiscount(Amount);

        int result = benefitInformation.calculateTotalDiscount(Amount);
        EventBadge eventBadge = new EventBadge(result);
        eventBadge.printEventBadge();

    }

    private void initialize() {
        OutputView.printWelcome();
        int dateInput = readDateUntilSuccess();
        date = new Date(dateInput);
        Map<String, Integer> menuOrder = readMenuAndQuantityForOrderUntilSuccess();

        menuOrder.entrySet().stream()
                .map(menu -> new Order(menu.getKey(), menu.getValue()))
                .forEach(orderRepository::addOrder);
        OutputView.printBenefit(date.getDate());
    }

    private int readDateUntilSuccess() {
        while (true) {
            try {
                int dateInput = inputView.readDate();
                return dateInput;
            }catch (IllegalArgumentException exception) {
                OutputView.printMessage(exception.getMessage());
            }
        }
    }

    private Map<String, Integer> readMenuAndQuantityForOrderUntilSuccess() {
        while (true) {
            try {
                Map<String, Integer> menuAndQuantityForOrder = inputView.readMenuAndQuantityForOrder();
                return menuAndQuantityForOrder;
            }catch (IllegalArgumentException exception) {
                OutputView.printMessage(exception.getMessage());
            }
        }
    }
}

