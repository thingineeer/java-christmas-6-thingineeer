package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventBadgeTest {

    EventBadge eventBadge;

    @BeforeEach
    void setUp() {
        eventBadge = new EventBadge(-31000);
    }

    @Test
    @DisplayName("printEventBadge() 뱃지 결과 출력 테스트")
    void 증정_메뉴_출력_주는지() {
        String expectedOutput = "\n<12월 이벤트 배지>\n산타\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        eventBadge.printEventBadge();

        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput);
    }
}