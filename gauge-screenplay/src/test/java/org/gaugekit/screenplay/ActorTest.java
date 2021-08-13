package org.gaugekit.screenplay;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ActorTest {

    Actor actor = new Actor("Tony");

    @Test
    @DisplayName("performs calls the task's performAs")
    void performsTest1() {
        final Task taskMock = mock(Task.class);

        actor.attemptTo(taskMock);

        verify(taskMock, times(1)).performAs(actor);
    }

    @Test
    @DisplayName("answers calls the question's answerAs")
    void answersTest1() {
        final String mockedAnswer = "Answer";
        @SuppressWarnings("unchecked") final Question<String> questionMock = (Question<String>) mock(Question.class);
        when(questionMock.answerAs(actor)).thenReturn(mockedAnswer);

        assertThat(actor.askFor(questionMock)).isEqualTo(mockedAnswer);

        verify(questionMock, times(1)).answerAs(actor);
    }

    @Test
    @DisplayName("can allows the actor to use the ability")
    void canTest1() {
        final Ability abilityMock = mock(Ability.class);

        assertThat(actor.can(abilityMock).uses(abilityMock.getClass()))
                .isEqualTo(abilityMock);
    }

    @Test
    @DisplayName("uses throws a MissingAbilityException")
    void usesTest1() {
        final Ability abilityMock = mock(Ability.class);
        final Class<? extends Ability> abilityClass = abilityMock.getClass();

        assertThatExceptionOfType(MissingAbilityException.class)
                .isThrownBy(() -> actor.uses(abilityClass));
    }

    @Test
    @DisplayName("remember makes the actor create a memory")
    void remember1() {
        final String avengers = "Avengers";
        String memory = actor.remember("team", avengers).recall("team");
        assertThat(memory).isEqualTo(avengers);
    }

    @Test
    @DisplayName("recall throws a MissingMemoryException")
    void remember2() {
        final String pepper = "Pepper";

        assertThatExceptionOfType(MissingMemoryException.class)
                .isThrownBy(() -> actor.recall(pepper));
    }
}
