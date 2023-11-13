package christmas.domain;

public class Order {

    private String menuName;
    private int menuQuantity;

    public Order(final String menuName, final int menuQuantity) {
        this.menuName = menuName;
        this.menuQuantity = menuQuantity;
    }

    public String printEachOrder() {
        return this.menuName + " " + this.menuQuantity + "개";
    }


}
