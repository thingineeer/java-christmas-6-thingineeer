package christmas.domain;

import christmas.view.OutputView;

public class EventBadge {
    // 이벤트 뱃지를 관리 하는 클래스 입니다.
    private int discountAmount;

    public EventBadge(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void printEventBadge() {
        OutputView.printSeparator();
        OutputView.printMessage(OutputView.EVENT_BADGE_MESSAGE);
        String badge = determineBadge(discountAmount);
        OutputView.printMessage(badge);
    }

    private String determineBadge(int discountAmount) {
        if (-discountAmount >= 20000) {
            return "산타";
        }
        if (-discountAmount >= 10000) {
            return "트리";
        }
        if (-discountAmount >= 5000) {
            return "별";
        }
        return "없음";
    }

}
