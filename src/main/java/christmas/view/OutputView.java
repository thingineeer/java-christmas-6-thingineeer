package christmas.view;

public class OutputView {
    public static final String NOTICE_BENEFIT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리보기!\n";
    public static final String WELCOME = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public static final String ORDER = "<주문 메뉴>";
    public static final String EVENT_MENU = "<증정 메뉴>";
    public static final String BENEFIT_DETAILS = "<혜택 내역>";
    public static final String TOTAL_BENEFIT_DETAILS = "<총혜택 금액>";
    public static final String AFTER_DISCOUNT_EXPECT = "<할인 후 예상 결제 금액>";
    public static final String EVENT_BADGE_MESSAGE = "<12월 이벤트 배지>";

    public static final String NOTHING = "없음";

    private OutputView() {
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printSeparator() {
        System.out.println();
    }

    public static void printBenefit(int date) {
        System.out.printf(NOTICE_BENEFIT, date);
    }
    public static void printWelcome() {
        printMessage(WELCOME);
    }

    public static void printOrderMenu() {
        printMessage(ORDER);
    }

}
