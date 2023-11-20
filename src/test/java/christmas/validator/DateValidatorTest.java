package christmas.validator;

import christmas.constant.ErrorMessage;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateValidatorTest {

    DateValidator dateValidator = new DateValidator();

    @Nested
    @DisplayName("CheckNumeric 검증 테스트")
    class CheckNumeric {
        @ParameterizedTest(name = "{0}가 입력되었을 때")
        @ValueSource(strings = {"1a", " ", "3&1", "1l", "..."})
        void 숫자_아닌_입력시_예외(String input) {
            AssertionsForClassTypes.assertThatThrownBy(() -> dateValidator.check(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.DATE_OUT_OF_RANGE.getMessage());
        }
    }

    @Nested
    @DisplayName("CheckOutOfRange 검증 테스트")
    class CheckOutOfRange {
        @ParameterizedTest(name = "{0}가 입력되었을 때")
        @ValueSource(strings = {"32", "-1", "45", "1000"})
        void 날짜_범위_넘어가면_예외(String input) {
            AssertionsForClassTypes.assertThatThrownBy(() -> dateValidator.check(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.DATE_OUT_OF_RANGE.getMessage());
        }
    }

}