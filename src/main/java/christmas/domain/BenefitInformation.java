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
    private static final String UNIT = "원";
    private static final int CHAMPAGNE_PRICE = 25000;
    private Date date;
    private OrderRepository orderRepository;

    public BenefitInformation(final Date date, final OrderRepository orderRepository) {
        this.date = date;
        this.orderRepository = orderRepository;
    }

    public void printDiscountInfo() {
        OutputView.printBenefit();
        int totalAmount = orderRepository.calculateTotalAmount();

        if (totalAmount < Constants.MINIMUM_DISCOUNT_ABLE_AMOUNT.getConstants()) {
            OutputView.printNothing();
            return;
        }
        List<String> discounts = calculateDiscounts(date, totalAmount);

        if (discounts.isEmpty()) {
            OutputView.printNothing();
            return;
        }
        printDiscountMessages(discounts);
    }

    public void printTotalDiscount(final int Amount) {
        OutputView.printSeparator();
        OutputView.printMessage(OutputView.TOTAL_BENEFIT_DETAILS);

        int totalAmount = Amount;

        if (totalAmount < Constants.MINIMUM_DISCOUNT_ABLE_AMOUNT.getConstants()) {
            OutputView.printMessage("0" + UNIT);
            return;
        }

        int result = calculateTotalDiscount(totalAmount);
        OutputView.printMessage(Utils.makeFormattedNumberWithComma(result) + UNIT);
    }


    public int calculateTotalDiscount(final int totalAmount) {
        int totalDiscount = 0;

        if (totalAmount > 10000) {
            totalDiscount += calculateChristmasDiscount(date);
            totalDiscount += calculateWeekdayWeekendDiscount(date);
            totalDiscount += calculateSpecialDiscount(date);
        }

        if (totalAmount > Constants.CHAMPAGNE_LIMIT.getConstants()) {
            totalDiscount -= CHAMPAGNE_PRICE;
        }

        return totalDiscount;
    }

    public void printPaymentAmountAfterDiscount(final int totalAmount) {
        OutputView.printSeparator();
        OutputView.printMessage(OutputView.AFTER_DISCOUNT_EXPECT);

        int result = calculateAfterDiscount(totalAmount);
        OutputView.printMessage(Utils.makeFormattedNumberWithComma(result) + UNIT);
    }

    private int calculateAfterDiscount(int totalAmount) { // 총 혜택 금액
        if (totalAmount > Constants.CHAMPAGNE_LIMIT.getConstants()) {
            totalAmount += CHAMPAGNE_PRICE;
        }
        int totalDiscount = calculateTotalDiscount(totalAmount);
        return totalAmount + totalDiscount;
    }

    private List<String> calculateDiscounts(final Date date, final int totalAmount) {
        List<String> discounts = new ArrayList<>();

        addDiscountInfo(discounts, "크리스마스 디데이 할인", calculateChristmasDiscount(date));
        addDiscountInfo(discounts, "평일 할인", calculateWeekdayWeekendDiscount(date));
        addDiscountInfo(discounts, "특별 할인", calculateSpecialDiscount(date));

        if (totalAmount > Constants.CHAMPAGNE_LIMIT.getConstants()) {
            addDiscountInfo(discounts, "증정 이벤트", -CHAMPAGNE_PRICE);
        }

        return discounts;
    }

    private void printDiscountMessages(final List<String> discounts) {
        discounts.forEach(OutputView::printMessage);
    }

    private void addDiscountInfo(final List<String> discounts, final String discountName, final int discountAmount) {
        if (discountAmount != 0) {
            discounts.add(discountName + ": " + Utils.makeFormattedNumberWithComma(discountAmount) + UNIT);
        }
    }

    private int calculateChristmasDiscount(final Date date) {
        int christmasDiscount = 0;
        int dayOfMonth = date.getDate();

        if (dayOfMonth >= Constants.EVENT_START_DATE.getConstants() && Constants.EVENT_END_DATE.constants <= 25) {
            int discountPerDay = 1000 + (dayOfMonth - 1) * 100; // 일일 할인액 계산
            christmasDiscount += discountPerDay; // 할인 적용
        }

        return -christmasDiscount;
    }


    private int calculateWeekdayWeekendDiscount(final Date date) {
        int weekdayWeekendDiscount = 0;

        LocalDate localDate = LocalDate.of(Constants.THIS_YEAR.getConstants(), Constants.EVENT_MONTH.getConstants(),
                date.getDate());
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
            weekdayWeekendDiscount = -calculateMainDiscount();
            return weekdayWeekendDiscount;
        }

        weekdayWeekendDiscount = -calculateDessertDiscount();
        return weekdayWeekendDiscount;
    }


    private int calculateDessertDiscount() {
        List<Order> orders = orderRepository.getOrderList();

        return orders.stream().filter(order -> Menu.getMenuName(order.retrieveMenuName()).getType() == MenuType.DESSERT)
                .mapToInt(order -> Constants.THIS_YEAR.getConstants() * order.retrieveMenuQuantity()).sum();
    }

    private int calculateMainDiscount() {
        List<Order> orders = orderRepository.getOrderList();

        return orders.stream().filter(order -> Menu.getMenuName(order.retrieveMenuName()).getType() == MenuType.MAIN)
                .mapToInt(order -> Constants.THIS_YEAR.getConstants() * order.retrieveMenuQuantity()).sum();
    }

    private int calculateSpecialDiscount(final Date date) {
        int specialDiscount = 0;

        int day = date.getDate();

        // 특별 날짜에 해당하는 경우 할인 적용
        if (day == 3 || day == 10 || day == 17 || day == 24 || day == 25 || day == 31) {
            specialDiscount = -1000;
        }

        return specialDiscount;
    }
}
