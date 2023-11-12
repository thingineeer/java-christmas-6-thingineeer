package christmas.validator;


import christmas.ErrorMessage;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderValidatorTest {

    OrderValidator orderValidator = new OrderValidator();

    @Nested
    @DisplayName("chechDefaultTemplate 검증 테스트")
    class CheckTemplate {
        @ParameterizedTest(name = "{0}가 입력되었을 때")
        @ValueSource(strings = {"1a", " ", "해산물파스타--2,레드와인-1", "..."})
        void 정해진_템플릿_입력이_아닌경우(String input) {
            AssertionsForClassTypes.assertThatThrownBy(() -> orderValidator.check(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.NOT_VALIDATED_ORDER.getMessage());
        }
    }

    @Nested
    @DisplayName("checkValidatedForm 검증 테스트")
    class CheckValidationForm {
        @ParameterizedTest(name = "{0}가 입력되었을 때")
        @ValueSource(strings = {"시저샐러드-1, 티본스테이크-T본10개", "해산물파스타-a,레드와인-1"})
        void 올바른_템플릿_이지만_유효하지_않을경우(String input) {
            AssertionsForClassTypes.assertThatThrownBy(() -> orderValidator.check(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.NOT_VALIDATED_ORDER.getMessage());
        }
    }

    @Nested
    @DisplayName("checkIfStringMenu 검증 테스트")
    class CheckStringMenuStyle {
        @ParameterizedTest(name = "{0}가 입력되었을 때")
        @ValueSource(strings = {"아이$스크림-1, 티본스*테이크-10", "해산물파스타-1,레?!와인-1", "양송이수프-1,시저샐러드-3,아이#스크림^^-10"
        })
        void 올바른_템플릿_입력했지만_메뉴이름이_올바르지_않을경우(String input) {
            AssertionsForClassTypes.assertThatThrownBy(() -> orderValidator.check(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.NOT_VALIDATED_ORDER.getMessage());
        }
    }

    @Nested
    @DisplayName("checkQuantityRange 검증 테스트")
    class CheckQuantityRange {
        @ParameterizedTest(name = "{0}가 입력되었을 때")
        @ValueSource(strings = {"바비큐립-21", "초코케이크-821,타파스-1", "티본스테이크-1,비비큐립-2,제로콜라-50",
                "제로콜라-5,레드와인-1,샴페인-9,티본스테이크-0"})
        void 각각의_메뉴의_수량이_1개이상_20개이하가_아닌경우(String input) {
            AssertionsForClassTypes.assertThatThrownBy(() -> orderValidator.check(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.NOT_VALIDATED_ORDER.getMessage());
        }
    }

    @Nested
    @DisplayName("checkIfMenuExists 검증 테스트")
    class CheckMenuExists {
        @ParameterizedTest(name = "{0}가 입력되었을 때")
        @ValueSource(strings = {"티본스테이크-5,블루와인-1", "엘본스테이크-1,크리스마스파스타-1, 제로콜라-10", "봉골레파스타-1,레드와인-10"})
        void 올바른_템플릿_입력했지만_없는_메뉴를_입력한경우(String input) {
            AssertionsForClassTypes.assertThatThrownBy(() -> orderValidator.check(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.NOT_VALIDATED_ORDER.getMessage());
        }
    }

    @Nested
    @DisplayName("checkForDuplicateMenu 검증 테스트")
    class CheckDuplicateMenu {
        @ParameterizedTest(name = "{0}가 입력되었을 때")
        @ValueSource(strings = {"시저샐러드-1,시저샐러드-1"})
        void 올바른_템플릿_입력했지만_중복된_메뉴를_입력한경우(String input) {
            AssertionsForClassTypes.assertThatThrownBy(() -> orderValidator.check(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.NOT_VALIDATED_ORDER.getMessage());
        }
    }

    @Nested
    @DisplayName("checkTotalMenuCount 검증 테스트")
    class CheckTotalMenuCount {
        @ParameterizedTest(name = "{0}가 입력되었을 때")
        @ValueSource(strings = {"양송이수프-1,시저샐러드-1,티본스테이크-15,샴페인-4"})
        void 올바른_템플릿_입력했지만_전체_메뉴의_수량이_20개_초과할경우(String input) {
            AssertionsForClassTypes.assertThatThrownBy(() -> orderValidator.check(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.TOTAL_MENU_COUNT_IS_OVER.getMessage());
        }
    }

    @Nested
    @DisplayName("checkBeverageOnly 검증 테스트")
    class CheckBeverageOnly {
        @ParameterizedTest(name = "{0}가 입력되었을 때")
        @ValueSource(strings = {"제로콜라-5,레드와인-1,샴페인-9", "제로콜라-19", "제로콜라-10,레드와인-10"})
        void 올바른_템플릿_입력했지만_음료메뉴만_시킨경우(String input) {
            AssertionsForClassTypes.assertThatThrownBy(() -> orderValidator.check(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.ORDER_ONLY_BEVERAGE.getMessage());
        }
    }
}