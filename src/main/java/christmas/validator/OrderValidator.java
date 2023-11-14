package christmas.validator;

import christmas.constant.ErrorMessage;
import christmas.constant.Menu;
import christmas.util.Utils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderValidator implements Validator {
    @Override
    public void check(String input) {
        chechDefaultTemplate(input);
        checkValidatedForm(input);
        checkIfStringMenu(input);
        checkQuantityRange(input);
        checkIfMenuExists(input);
        checkForDuplicateMenu(input);
        checkTotalMenuCount(input);
        checkBeverageOnly(input);
    }

    private void chechDefaultTemplate(String input) {

        List<String> orderItems = Arrays.asList(input.split(","));

        orderItems.stream()
                .map(item -> item.split("-"))
                .forEach(parts -> {
                    try {
                        if (parts.length != 2 || !parts[1].matches("\\d+")) {
                            throw new IllegalArgumentException(ErrorMessage.NOT_VALIDATED_ORDER.getMessage());
                        }
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException(ErrorMessage.NOT_VALIDATED_ORDER.getMessage());
                    }
                });

    }

    private void checkValidatedForm(String input) {
        // 유효하지않은 입력 (기본 템플릿 입력이 아닌 경우) [요청 사항 존재]
        try {
            Map<String, Integer> resultMap = Stream.of(input.split(","))
                    .map(s -> s.split("-")).collect(Collectors.toMap(
                            arr -> arr[0],
                            arr -> Integer.parseInt(arr[1]),
                            (existing, replacement) -> existing,
                            HashMap::new
                    ));
            // {"해산물파스타": 2, "레드와인": 1, ...}
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_VALIDATED_ORDER.getMessage());
        }
    }

    private void checkIfStringMenu(String input) {
        Map<String, Integer> menu = Utils.makeStringToHashMap(input);
        // 메뉴(key) 부분이 String, 즉 문자열(한글,영어)이 아니면 예외처리 [요청 템플릿]
        menu.keySet().stream()
                .filter(key -> !key.matches("[a-zA-Z가-힣]+"))
                .findAny()
                .ifPresent(invalidKey -> {
                    throw new IllegalArgumentException(ErrorMessage.NOT_VALIDATED_ORDER.getMessage());
                });
    }

    private void checkQuantityRange(String input) {
        // 수량이 각각 1이상 20이하 가 아니면 예외처리 [요청 사항 존재]
        try {
            Map<String, Integer> menu = Utils.makeStringToHashMap(input);
            menu.entrySet().stream()
                    .filter(entry -> entry.getValue() < 1 || entry.getValue() > 20)
                    .findAny()
                    .ifPresent(entry -> {
                        throw new IllegalArgumentException(ErrorMessage.NOT_VALIDATED_ORDER.getMessage());
                    });
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_VALIDATED_ORDER.getMessage());
        }
    }

    private void checkIfMenuExists(String input) {
        // 없는 메뉴일지 예외처리 ( ex) T본스테이크 ) [요청 사항 존재]
        Map<String, Integer> menu = Utils.makeStringToHashMap(input);

        Set<String> availableMenuItems = Arrays.stream(Menu.values())
                .map(menuEnum -> menuEnum.getName())
                .collect(Collectors.toSet());

        menu.keySet().stream()
                .filter(inputMenu -> !availableMenuItems.contains(inputMenu))
                .findAny()
                .ifPresent(invalidKey -> {
                    throw new IllegalArgumentException(ErrorMessage.NOT_VALIDATED_ORDER.getMessage());
                });
    }

    private void checkForDuplicateMenu(String input) {
        Map<String, Integer> resultMap = Stream.of(input.split(","))
                .map(s -> s.split("-"))
                .collect(Collectors.toMap(
                        arr -> arr[0],
                        arr -> Integer.parseInt(arr[1]),
                        (existing, replacement) -> {
                            throw new IllegalArgumentException(ErrorMessage.NOT_VALIDATED_ORDER.getMessage());
                        },
                        HashMap::new
                ));
    }

    private void checkTotalMenuCount(String input) {
        // 메뉴 개수의 합이 20개가 초과할시 예외처리
        Map<String, Integer> menu = Utils.makeStringToHashMap(input);
        int total = menu.values().stream().mapToInt(Integer::intValue).sum();

        if (total > 20) {
            throw new IllegalArgumentException(ErrorMessage.TOTAL_MENU_COUNT_IS_OVER.getMessage());
        }
    }

    private void checkBeverageOnly(String input) {
        // 각 메뉴에 음료 클래스만 있으면 예외처리

        Map<String, Integer> menu = Utils.makeStringToHashMap(input);
        List<String> onlyBeverage = Menu.BEVERAGE_MENU;

        List<String> matchCount = menu.keySet().stream()
                .filter(onlyBeverage::contains)
                .toList();

        if (menu.size() == matchCount.size()) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ONLY_BEVERAGE.getMessage());
        }
    }
}
