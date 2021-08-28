package org.gaugekit.core.screenplay;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class RetryableTaskTest {

    RetryableTask retryableTask = (actor) -> {};

    @Test
    @DisplayName("getAcknowledgedExceptions is empty by default")
    void retryable1() {
        assertThat(retryableTask.getAcknowledgedExceptions()).isEqualTo(Collections.EMPTY_SET);
    }
}