package christmas.domain;

import christmas.constant.Constants;
import christmas.view.OutputView;

public class GiftMenu {
    // 증정 메뉴에 관한 정보를 담당하는 클래스로, 증정 메뉴를 받을수 있는지 확인합니다.
    private int totalPayment;

    public GiftMenu(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    public void printGiftInfo() {
        OutputView.printGiftMenu();
        if (totalPayment >= Constants.CHAMPAGNE_LIMIT.getConstants()) {
            OutputView.printMessage("샴페인 1개");
            return;
        }
        OutputView.printMessage("없음");
    }
}
