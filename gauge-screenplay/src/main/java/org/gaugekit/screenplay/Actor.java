package org.gaugekit.screenplay;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static java.time.Instant.now;
import static java.util.Objects.requireNonNull;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * An {@link Actor} is the central class of the screenplay pattern. It is basically used for any interaction with the
 * system under test.
 */
public final class Actor {

    private final String name;

    /**
     * A {@link Map} of {@link Ability}s the {@link Actor}'s posses.
     */
    private final Map<Class<? extends Ability>, Ability> abilities = new HashMap<>();

    /**
     * A {@link Map} of the memories the {@link Actor} remembers.
     */
    private final HashMap<Object, Object> memories = new HashMap<>();

    /**
     * @param name a name used for logging and reporting
     */
    public Actor(String name) {
        this.name = requireNonNull(name);
    }

    public static Actor named(String name) {
        return new Actor(name);
    }

    /**
     * @param task the {@link Task} to be performed by this {@link Actor}
     * @return this {@link Actor}
     */
    public Actor attemptTo(Task task) {
        task.performAs(this);
        return this;
    }

    /**
     * @param task the {@link RetryableTask} to be performed by this {@link Actor}
     * @return this {@link Actor}
     * @throws TimeoutException if no acceptable answer is given when the question's timeout is reached
     */
    public Actor attemptTo(RetryableTask task) {
        final Duration timeout = task.getTimeout();
        final long intervalMillis = task.getInterval().toMillis();
        final Instant end = now().plus(timeout);

        Exception lastException;

        while (true) {
            try {
                task.performAs(this);
                return this;
            } catch (Exception e) {
                lastException = e;
                if (task.getAcknowledgedExceptions().stream().anyMatch(acknowledge -> acknowledge.isInstance(e))) {
                    throw e;
                }
            }

            if (now().isAfter(end)) {
                throw new TimeoutException(this, task, lastException);
            }

            try {
                sleep(intervalMillis);
            } catch (InterruptedException e) {
                currentThread().interrupt();
                throw new RetryInterruptedException(this, task, e);
            }
        }
    }

    /**
     * @param question the {@link Question} to be answered by this {@link Actor}
     * @param <A>      the {@link Class} of the answer
     * @return the answer to the given Question
     */
    public <A> A askFor(Question<A> question) {
        return question.answerAs(this);
    }

    /**
     * @param question the {@link RetryableQuestion} to be answered by this {@link Actor}
     * @param <A>      the {@link Class} of the answer
     * @return the answer to the given Question
     * @throws TimeoutException if no acceptable answer is given when the question's timeout is reached
     */
    public <A> A askFor(RetryableQuestion<A> question) {
        final Duration timeout = question.getTimeout();
        final long intervalMillis = question.getInterval().toMillis();
        final Instant end = now().plus(timeout);

        Exception lastException;
        A lastAnswer;

        while (true) {
            try {
                lastAnswer = question.answerAs(this);
                lastException = null;

                if (question.acceptable(lastAnswer)) {
                    return lastAnswer;
                }
            } catch (Exception e) {
                lastException = e;
                if (question.getIgnoredExceptions().stream().noneMatch(ignore -> ignore.isInstance(e))) {
                    throw e;
                }
            }

            if (now().isAfter(end)) {
                throw new TimeoutException(this, question, lastException);
            }

            try {
                sleep(intervalMillis);
            } catch (InterruptedException e) {
                currentThread().interrupt();
                throw new RetryInterruptedException(this, question, e);
            }
        }
    }

    /**
     * @param abilities {@link Ability}s the {@link Actor} may {@link #uses}
     * @return this {@link Actor}
     */
    public Actor can(Ability... abilities) {
        this.abilities.putAll(Arrays.stream(abilities).collect(toMap(Ability::getClass, identity())));
        return this;
    }

    /**
     * @param abilityClass the {@link Ability} {@link Class} that should be used
     * @param <A>          the required {@link Ability} {@link Class}
     * @return the {@link Ability} instance from the {@link Actor}'s {@link #abilities}
     * @throws MissingAbilityException if there's no instance of the requested {@link Ability} {@link Class} in the
     *                                 {@link Actor}'s {@link #abilities}
     */
    public <A extends Ability> A uses(Class<A> abilityClass) {
        return Optional.ofNullable(abilities.get(abilityClass))
                .map(abilityClass::cast)
                .orElseThrow(() -> new MissingAbilityException(this, abilityClass));
    }

    /**
     * @param name   the name of memory the {@link Actor} {@link #remember}s
     * @param memory the memory the {@link Actor} {@link #remember}s
     * @return this {@link Actor}
     */
    public Actor remember(String name, Object memory) {
        memories.put(name, memory);
        return this;
    }

    public Actor remember(String name, Question question) {
        memories.put(name, askFor(question));
        return this;
    }

    /**
     * @param name the name of the memory the {@link Actor} {@link #recall}s
     * @return the {@link Memory} instance for the {@link Actor}'s {@link #memories}
     * @throws MissingMemoryException if there's no instance of the requested {@link Memory} {@link Class} in the
     *                                {@link Actor}'s {@link #memories}
     */
    public <T> T recall(String name) {
        T fact = (T) memories.get(name);
        if (fact == null) {
            throw new MissingMemoryException(this, name);
        }
        return fact;
    }

    public void cleanUp() {
        abilities.values().stream().forEach(Ability::cleanUp);
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Actor that = (Actor) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.abilities, that.abilities) &&
                Objects.equals(this, that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, abilities);
    }

}
