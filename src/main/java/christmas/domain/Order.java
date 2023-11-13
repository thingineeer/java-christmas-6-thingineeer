package christmas.domain;

public class Order {
    private String menuName;
    private int menuQuantity;

    public Order(final String menuName, final int menuQuantity) {
        this.menuName = menuName;
        this.menuQuantity = menuQuantity;
    }

    public String getEachOrder() {
        return this.menuName + " " + this.menuQuantity + "개";
    }


}
