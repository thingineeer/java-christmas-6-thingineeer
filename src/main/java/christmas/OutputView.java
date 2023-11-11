package christmas;

public final class OutputView {

    private OutputView() {
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printWelcome() {
        printMessage("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printMenu() {
        printMessage("<주문 메뉴>");
    }


}
