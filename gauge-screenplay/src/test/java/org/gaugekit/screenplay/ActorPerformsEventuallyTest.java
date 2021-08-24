package org.gaugekit.screenplay;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

class ActorPerformsEventuallyTest {

    Actor actor = new Actor("Tony");

    @Test
    @DisplayName("attemptsTo calls the task's performAs until its timeout")
    void attemptsTo1() {
        final RetryableTask retryableTaskMock = mock(RetryableTask.class);
        when(retryableTaskMock.getTimeout()).thenReturn(Duration.ofMillis(100));
        when(retryableTaskMock.getInterval()).thenReturn(Duration.ofMillis(10));
        doThrow(new RuntimeException()).when(retryableTaskMock).performAs(actor);

        assertThatExceptionOfType(TimeoutException.class)
                .isThrownBy(() -> actor.attemptsTo(retryableTaskMock));

        verify(retryableTaskMock, atLeast(2)).performAs(actor);
    }

    @Test
    @DisplayName("attemptsTo returns immediately after success")
    void attemptsTo2() {
        final RetryableTask retryableTaskMock = mock(RetryableTask.class);
        when(retryableTaskMock.getTimeout()).thenReturn(Duration.ofMillis(100));
        when(retryableTaskMock.getInterval()).thenReturn(Duration.ofMillis(10));

        actor.attemptsTo(retryableTaskMock);

        verify(retryableTaskMock, times(1)).performAs(actor);
    }

    @Test
    @DisplayName("attemptsTo throws acknowledged exceptions immediately")
    void attemptsTo3() {
        final RetryableTask retryableTaskMock = mock(RetryableTask.class);
        when(retryableTaskMock.getTimeout()).thenReturn(Duration.ofMillis(100));
        when(retryableTaskMock.getInterval()).thenReturn(Duration.ofMillis(10));

        doThrow(new IllegalStateException()).when(retryableTaskMock).performAs(actor);
        when(retryableTaskMock.getAcknowledgedExceptions()).thenReturn(Collections.singleton(IllegalStateException.class));

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> actor.attemptsTo(retryableTaskMock));

        verify(retryableTaskMock, times(1)).performAs(actor);
    }

}