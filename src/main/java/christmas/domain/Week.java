package christmas.domain;

public enum Week {
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7);

    private final int week;

    Week(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }
}
