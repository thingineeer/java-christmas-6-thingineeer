package christmas.domain;

public class Order {
    private static final String UNIT = "개";
    private String menuName;
    private int menuQuantity;

    public Order(final String menuName, final int menuQuantity) {
        this.menuName = menuName;
        this.menuQuantity = menuQuantity;
    }

    public String getOrderDetail() {
        return this.menuName + " " + this.menuQuantity + UNIT;
    }

    @Override
    public String toString() {
        return getOrderDetail();
    }

    public String retrieveMenuName() {
        return menuName;
    }

    public int retrieveMenuQuantity() {
        return menuQuantity;
    }
}
