package christmas.constant;

import java.time.LocalDate;

public enum Constants {
    START_DATE(1),
    END_DATE(31),
    MENU_LIMIT(20),
    CHAMPAGNE_LIMIT(120000),
    THIS_YEAR(LocalDate.now().getYear());

    public int constants;

    Constants(final int constants) {
        this.constants = constants;
    }

    public int getConstants() {
        return constants;
    }
}
