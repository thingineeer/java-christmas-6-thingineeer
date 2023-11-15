package christmas.constant;

import java.time.LocalDate;

public enum Constants {
    EVENT_START_DATE(1),
    EVENT_END_DATE(25),
    MENU_LIMIT(20),
    CHAMPAGNE_LIMIT(120000),
    MINIMUM_DISCOUNT_ABLE_AMOUNT(10000),
    THIS_YEAR(LocalDate.now().getYear()),
    EVENT_MONTH(12);

    public int constants;

    Constants(final int constants) {
        this.constants = constants;
    }

    public int getConstants() {
        return constants;
    }
}
