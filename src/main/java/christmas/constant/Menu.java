package christmas.constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum Menu {
    // Appetizers
    MUSHROOM_SOUP("양송이수프", 6000, MenuType.APPETIZER),
    TAPAS("타파스", 5500, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, MenuType.APPETIZER),

    // Main courses
    T_BONE_STEAK("티본스테이크", 55000, MenuType.MAIN),
    BBQ_RIB("바비큐립", 54000, MenuType.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MenuType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MenuType.MAIN),

    // Desserts
    CHOCOLATE_CAKE("초코케이크", 15000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5000, MenuType.DESSERT),

    // Beverages
    ZERO_COLA("제로콜라", 3000, MenuType.BEVERAGE),
    RED_WINE("레드와인", 60000, MenuType.BEVERAGE),
    CHAMPAGNE("샴페인", 25000, MenuType.BEVERAGE);

    public static final List<String> BEVERAGE_MENU = Stream.of(Menu.values())
            .filter(i -> i.getType() == MenuType.BEVERAGE)
            .map(i -> i.getName())
            .toList();

    public static final List<String> ALL_MENU_NAME = Arrays.stream(Menu.values())
            .map(menuEnum -> menuEnum.getName())
            .toList();

    private final String name;
    private final int price;
    private final MenuType type;

    Menu(final String name, final int price, final MenuType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public MenuType getType() {
        return type;
    }

    public static Menu getMenuName(final String koreanName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(koreanName))
                .findFirst()
                .orElse(null);
                // 하나도 없으면 null
    }
}

