package org.gaugekit.screenplay;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ActorAnswersEventuallyTest {

    Actor actor = new Actor("Tony");

    @Test
    @DisplayName("askFor calls the question's answeredAs until its timeout")
    void askFor1() {
        final RetryableQuestion retryableQuestionMock = mock(RetryableQuestion.class);
        when(retryableQuestionMock.getTimeout()).thenReturn(Duration.ofMillis(100));
        when(retryableQuestionMock.getInterval()).thenReturn(Duration.ofMillis(10));
        when(retryableQuestionMock.acceptable(any())).thenReturn(false);

        assertThatExceptionOfType(TimeoutException.class)
                .isThrownBy(() -> actor.askFor(retryableQuestionMock));

        verify(retryableQuestionMock, atLeast(2)).answerAs(actor);
    }

    @Test
    @DisplayName("askFor returns acceptable answers immediately")
    void askFor2() {
        final Object answer = new Object();
        final RetryableQuestion retryableQuestionMock = mock(RetryableQuestion.class);
        when(retryableQuestionMock.getTimeout()).thenReturn(Duration.ofMillis(100));
        when(retryableQuestionMock.getInterval()).thenReturn(Duration.ofMillis(10));

        when(retryableQuestionMock.acceptable(any())).thenReturn(true);
        when(retryableQuestionMock.answerAs(actor)).thenReturn(answer);

        assertThat(actor.askFor(retryableQuestionMock)).isEqualTo(answer);

        verify(retryableQuestionMock, times(1)).answerAs(actor);
    }

    @Test
    @DisplayName("askFor catches ignored exceptions immediately")
    void askFor3() {
        final RetryableQuestion retryableQuestionMock = mock(RetryableQuestion.class);
        when(retryableQuestionMock.getTimeout()).thenReturn(Duration.ofMillis(100));
        when(retryableQuestionMock.getInterval()).thenReturn(Duration.ofMillis(10));

        doThrow(new IllegalStateException()).when(retryableQuestionMock).answerAs(actor);
        when(retryableQuestionMock.getIgnoredExceptions()).thenReturn(Collections.singleton(IllegalStateException.class));

        assertThatExceptionOfType(TimeoutException.class)
                .isThrownBy(() -> actor.askFor(retryableQuestionMock));

        verify(retryableQuestionMock, atLeast(2)).answerAs(actor);
    }

    @Test
    @DisplayName("askFor throws not ignored exceptions immediately")
    void askFor4() {
        final RetryableQuestion retryableQuestionMock = mock(RetryableQuestion.class);
        when(retryableQuestionMock.getTimeout()).thenReturn(Duration.ofMillis(100));
        when(retryableQuestionMock.getInterval()).thenReturn(Duration.ofMillis(10));

        doThrow(new IllegalStateException()).when(retryableQuestionMock).answerAs(actor);
        when(retryableQuestionMock.getIgnoredExceptions()).thenReturn(Collections.EMPTY_SET);

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> actor.askFor(retryableQuestionMock));

        verify(retryableQuestionMock, times(1)).answerAs(actor);
    }

}