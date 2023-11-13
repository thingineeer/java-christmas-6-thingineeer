package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiftMenuTest {
    GiftMenu giftMenu;

    @BeforeEach
    void setUp() {
        giftMenu = new GiftMenu(120000);
    }

    @Test
    @DisplayName("printGiftInfo() 12만원 이상 출력 테스트")
    void 증정_메뉴_출력_주는지() {
        String expectedOutput = "\n<증정 메뉴>\n샴페인 1개\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        giftMenu.printGiftInfo();

        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    @DisplayName("printGiftInfo() 12만원 미만 출력 테스트")
    void 증정_메뉴_출력_안주는지() {
        String expectedOutput = "\n<증정 메뉴>\n없음\n";
        giftMenu = new GiftMenu(119999);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        giftMenu.printGiftInfo();

        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput);
    }
}