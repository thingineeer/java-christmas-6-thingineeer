package christmas.domain;

import christmas.view.OutputView;

public class EventBadge {
    // 이벤트 뱃지를 관리 하는 클래스 입니다.
    private static final int SANTA_MINIMUM_LIMIT = 20000;
    private static final int TREE_MINIMUM_LIMIT = 20000;
    private static final int STAR_MINIMUM_LIMIT = 20000;
    private static final String SANTA = "산타";
    private static final String TREE = "트리";
    private static final String STAR = "별";

    private static final String NOTHING = "없음";

    private int discountAmount;

    public EventBadge(final int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void printEventBadge() {
        OutputView.printSeparator();
        OutputView.printMessage(OutputView.EVENT_BADGE_MESSAGE);
        String badge = determineBadge(discountAmount);
        OutputView.printMessage(badge);
    }

    private String determineBadge(final int discountAmount) {
        if (-discountAmount >= SANTA_MINIMUM_LIMIT) {
            return SANTA;
        }
        if (-discountAmount >= TREE_MINIMUM_LIMIT) {
            return TREE;
        }
        if (-discountAmount >= STAR_MINIMUM_LIMIT) {
            return STAR;
        }
        return NOTHING;
    }
}
