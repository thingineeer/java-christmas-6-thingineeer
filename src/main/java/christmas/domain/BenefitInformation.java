package christmas.domain;

import christmas.constant.Constants;
import christmas.constant.Menu;
import christmas.constant.MenuType;
import christmas.util.Utils;
import christmas.view.OutputView;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BenefitInformation {
    // 혜택내역과 혜택 금액을 관리하는 클래스 입니다.
    private Date date;
    private OrderRepository orderRepository;

    public BenefitInformation(Date date, OrderRepository orderRepository) {
        this.date = date;
        this.orderRepository = orderRepository;
    }

    public void printDiscountInfo() {
        OutputView.printBenefit();

        int totalAmount = orderRepository.calculateTotalAmount();

        if (totalAmount < 10000) {
            OutputView.printNothing();
            return;
        }

        List<String> discounts = new ArrayList<>();

        addDiscountInfo(discounts, "크리스마스 디데이 할인", calculateChristmasDiscount(date));
        addDiscountInfo(discounts, "평일 할인", calculateWeekdayWeekendDiscount(date));
        addDiscountInfo(discounts, "특별 할인", calculateSpecialDiscount(date));

        if (totalAmount > Constants.CHAMPAGNE_LIMIT.getConstants()) {
            addDiscountInfo(discounts, "증정 이벤트", -25000);
        }

        if (discounts.isEmpty()) {
            OutputView.printNothing();
            return;
        }

        discounts.forEach(System.out::println);
    }

    private void addDiscountInfo(List<String> discounts, String discountName, int discountAmount) {
        if (discountAmount != 0) {
            discounts.add(discountName + ": " + Utils.makeFormattedNumberWithComma(discountAmount) + "원");
        }
    }

    public void printTotalDiscount(int Amount) {
        OutputView.printSeparator();
        OutputView.printMessage(OutputView.TOTAL_BENEFIT_DETAILS);

        int totalAmount = Amount;

        if (totalAmount < 10000) {
            OutputView.printMessage("0원");
            return;
        }

        int result = calculateTotalDiscount(totalAmount);
        OutputView.printMessage(Utils.makeFormattedNumberWithComma(result) + "원");
    }


    public int calculateTotalDiscount(int totalAmount) {
        int totalDiscount = 0;

        if (totalAmount > 10000) {
            totalDiscount += calculateChristmasDiscount(date);
            totalDiscount += calculateWeekdayWeekendDiscount(date);
            totalDiscount += calculateSpecialDiscount(date);
        }

        if (totalAmount > 120000) {
            totalDiscount -= 25000;
        }

        return totalDiscount;
    }

    private int calculateChristmasDiscount(Date date) {
        int christmasDiscount = 1000;
        int dayOfMonth = date.getDate();

        if (dayOfMonth >= 1 && dayOfMonth <= 25) {
            int discountPerDay = (dayOfMonth - 1) * 100; // 일일 할인액 계산
            christmasDiscount += discountPerDay; // 할인 적용
        }

        return -christmasDiscount;
    }


    private int calculateWeekdayWeekendDiscount(Date date) {
        int weekdayWeekendDiscount = 0;

        LocalDate localDate = LocalDate.of(2023, 12, date.getDate());
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.FRIDAY) {
            weekdayWeekendDiscount = -calculateMainDiscount();
            return weekdayWeekendDiscount;
        }

        weekdayWeekendDiscount = -calculateDessertDiscount();
        return weekdayWeekendDiscount;
    }


    private int calculateDessertDiscount() {
        List<Order> orders = orderRepository.getOrderList();

        return orders.stream()
                .filter(order -> Menu.getMenuName(order.retrieveMenuName()).getType() == MenuType.DESSERT)
                .mapToInt(order -> Constants.THIS_YEAR.getConstants() * order.retrieveMenuQuantity())
                .sum();
    }

    private int calculateMainDiscount() {
        List<Order> orders = orderRepository.getOrderList();

        return orders.stream()
                .filter(order -> Menu.getMenuName(order.retrieveMenuName()).getType() == MenuType.MAIN)
                .mapToInt(order -> Constants.THIS_YEAR.getConstants() * order.retrieveMenuQuantity())
                .sum();
    }

    private int calculateSpecialDiscount(Date date) {
        int specialDiscount = 0;

        int day = date.getDate();

        // 특별 날짜에 해당하는 경우 할인 적용
        if (day == 3 || day == 10 || day == 17 || day == 24 || day == 25 || day == 31) {
            specialDiscount = -1000;
        }

        return specialDiscount;
    }
}
