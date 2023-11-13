package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.InputView;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {
    private InputView inputView;

    @BeforeEach
    void setUp() {
        inputView = new InputView();
    }

    private void setInput(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }

    @Test
    void 숫자_입력_잘되는지() {
        String input = "5";
        setInput(input);

        int date = inputView.readDate();

        assertThat(date).isEqualTo(5);
    }

    @Test
    @DisplayName("readMenuAndQuantityForOrder 검증 테스트")
    void 잘_쪼개지는지() {
        String input = "해산물파스타-2,레드와인-1,초코케이크-1";
        setInput(input);

        Map<String, Integer> Menu = inputView.readMenuAndQuantityForOrder();

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("해산물파스타", 2);
        map.put("레드와인", 1);
        map.put("초코케이크", 1);

        assertThat(Menu).isEqualTo(map);
    }

    @Test
    @DisplayName("readMenuAndQuantityForOrder 검증 테스트")
    void 잘_쪼개지는지2() {
        String input = "양송이수프-1,티본스테이크-15,샴페인-3,레드와인-1";
        setInput(input);

        Map<String, Integer> Menu = inputView.readMenuAndQuantityForOrder();

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("양송이수프", 1);
        map.put("티본스테이크", 15);
        map.put("샴페인", 3);
        map.put("레드와인",1);

        assertThat(Menu).isEqualTo(map);
    }

    @AfterEach
    void closeConsole() {
        Console.close();
    }

}