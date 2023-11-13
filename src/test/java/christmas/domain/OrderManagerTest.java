package christmas.domain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import christmas.view.OutputView;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderManagerTest {

    OrderRepository orderRepository = new OrderRepository();
    Order T_Born;
    Order BBQ;
    OrderManager orderManager;


    @BeforeEach
    void setUp() {
        T_Born = new Order("티본스테이크", 1);
        BBQ = new Order("바비큐립", 4);
        orderRepository.addOrder(T_Born);
        orderRepository.addOrder(BBQ);
        orderManager = new OrderManager(orderRepository); // 생성자로 초기화
    }


    @Test
    @DisplayName("printAllDetail() 출력 테스트")
    void 주문메뉴_잘_출력되는지() {


        // 예상 출력 결과
        String expectedOutput = "\n<주문 메뉴>\n티본스테이크 1개\n바비큐립 4개\n";

        // printAllDetail() 메서드 실행
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        orderManager.printAllDetail();

        // 출력 결과 확인
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput);

    }
}