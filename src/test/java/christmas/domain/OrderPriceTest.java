package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderPriceTest {
    OrderRepository orderRepository = new OrderRepository();
    Order T_Born;
    Order BBQ;
    OrderPrice orderPrice;

    @BeforeEach
    void setUp() {
        T_Born = new Order("티본스테이크", 1);
        BBQ = new Order("바비큐립", 4);
        orderRepository.addOrder(T_Born);
        orderRepository.addOrder(BBQ);
        orderPrice = new OrderPrice(orderRepository);
    }

    @Test
    @DisplayName("printBeforeDiscountInfo() 출력 테스트")
    void 주문메뉴의_총_금액이_잘_출력되는지() {
        String expectedOutput = "\n<할인 전 총주문 금액>\n271,000원\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        orderPrice.printBeforeDiscountInfo();

        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @AfterEach
    void closeConsole() {
        Console.close();
    }
}