package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    private Order orderTapas;
    private Order orderZeroCoke;
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderTapas = new Order("타파스", 1);
        orderZeroCoke = new Order("제로콜라", 17);
    }

    @Test
    @DisplayName("printEachOrderTest 검증 테스트")
    void 각_주문_출력이_잘되는지() {
        String expect = "타파스 1개";
        String expect2 = "제로콜라 17개";

        String result = orderTapas.toString();
        String result2 = orderZeroCoke.toString();

        assertThat(result).isEqualTo(expect);
        assertThat(result2).isEqualTo(expect2);
    }


}