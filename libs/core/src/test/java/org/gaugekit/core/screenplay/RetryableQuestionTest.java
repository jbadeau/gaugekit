package org.gaugekit.core.screenplay;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class RetryableQuestionTest {

    RetryableQuestion<Object> retryableQuestion = (actor) -> true;

    @Test
    @DisplayName("getIgnoredExceptions is empty by default")
    void getIgnoredExceptionsTest1() {
        assertThat(retryableQuestion.getIgnoredExceptions()).isEqualTo(Collections.EMPTY_SET);
    }

    @ParameterizedTest(name = "acceptable accepts {0} by default")
    @MethodSource("acceptableAnswers")
    void acceptableTest1(Object answer) {
        assertThat(retryableQuestion.acceptable(answer)).isTrue();
    }

    static Object[] acceptableAnswers() {
        final Object something = new Object();
        return new Object[]{
                Optional.of(something),
                Collections.singletonList(something),
                Collections.singleton(something),
                Collections.singletonMap("key", something),
                new Object[]{something},
                true,
                Boolean.TRUE,
                something,
                "some string"
        };
    }

    @ParameterizedTest(name = "acceptable declines {0} by default")
    @MethodSource("unacceptableAnswers")
    void acceptableTest2(Object answer) {
        assertThat(retryableQuestion.acceptable(answer)).isFalse();
    }

    static Object[] unacceptableAnswers() {
        return new Object[]{
                Optional.empty(),
                Collections.EMPTY_LIST,
                Collections.EMPTY_SET,
                Collections.EMPTY_MAP,
                false,
                Boolean.FALSE,
                null
        };
    }

    @Test
    @DisplayName("acceptable declines empty arrays by default")
    void acceptableTest3() {
        assertThat(retryableQuestion.acceptable(new Object[]{})).isFalse();
    }
}