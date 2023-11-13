package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitInformationTest {
    OrderRepository orderRepository;
    Date date;
    BenefitInformation benefitInformation;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();
    }

    @Test
    @DisplayName("printDiscountInfo() 3일 혜택 내역 적용")
    void 날짜_3_일요일_적용() {
        prepareOrdersForTest1();

        date = new Date(3);
        benefitInformation = new BenefitInformation(date, orderRepository);

        assertDiscountInfo("\n<혜택 내역>\n"
                + "크리스마스 디데이 할인: -1,200원\n"
                + "평일 할인: -4,046원\n"
                + "특별 할인: -1,000원\n"
                + "증정 이벤트: -25,000원\n");
    }

    @Test
    @DisplayName("printDiscountInfo() 15일 혜택 내역 적용")
    void 날짜_15_금요일_적용() {
        prepareOrdersForTest2();

        date = new Date(15);
        benefitInformation = new BenefitInformation(date, orderRepository);

        assertDiscountInfo("\n<혜택 내역>\n"
                + "크리스마스 디데이 할인: -2,400원\n");
    }

    @Test
    @DisplayName("printDiscountInfo() 26일 혜택 내역 적용")
    void 날짜_26_화요일_적용() {
        prepareOrdersForTest3();

        date = new Date(26);
        benefitInformation = new BenefitInformation(date, orderRepository);

        assertDiscountInfo("\n<혜택 내역>\n"
                + "없음\n");
    }

    @Test
    void 날짜_확인() {
        LocalDate localDate = LocalDate.of(2023, 12, 15);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        assertEquals(dayOfWeek, DayOfWeek.FRIDAY);
    }

    private void prepareOrdersForTest1() {
        orderRepository.addOrder(new Order("티본스테이크", 1));
        orderRepository.addOrder(new Order("바비큐립", 1));
        orderRepository.addOrder(new Order("초코케이크", 2));
        orderRepository.addOrder(new Order("제로콜라", 1));
    }

    private void prepareOrdersForTest2() {
        orderRepository.addOrder(new Order("초코케이크", 2));
        orderRepository.addOrder(new Order("제로콜라", 1));
    }

    private void prepareOrdersForTest3() {
        orderRepository.addOrder(new Order("제로콜라", 1));
        orderRepository.addOrder(new Order("타파스", 1));
    }

    private void assertDiscountInfo(String expectedOutput) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        benefitInformation.printDiscountInfo();

        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput);
    }


}