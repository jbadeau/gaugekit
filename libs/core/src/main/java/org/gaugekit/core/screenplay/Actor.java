package org.gaugekit.core.screenplay;


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

    private String pronoun;

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

    public String getName() {
        return name;
    }

    public String getNameOrPronoun() {
        return (pronoun != null) ? pronoun : name;
    }

    public Actor usingPronoun(String pronoun) {
        this.pronoun = pronoun;
        return this;
    }

    public Actor withNoPronoun() {
        this.pronoun = null;
        return this;
    }

    /**
     * @param tasks the {@link Task}s to be performed by this {@link Actor}
     * @return this {@link Actor}
     */
    public Actor attemptsTo(Task... tasks) {
        for (Task performable : tasks) {
            performable.performAs(this);
        }
        return this;
    }

    /**
     * @param question the {@link Question} to be answered by this {@link Actor}
     * @param <A>      the {@link Class} of the answer
     * @return the answer to the given Question
     */
    public <A> A asksFor(Question<A> question) {
        return question.answerAs(this);
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
     * @param name   the name of memory the {@link Actor} {@link #memorizes}s
     * @param memory the memory the {@link Actor} {@link #memorizes}s
     * @return this {@link Actor}
     */
    public Actor memorizes(String name, Object memory) {
        memories.put(name, memory);
        return this;
    }

    /**
     * @param name     the name of memory the {@link Actor} {@link #memorizes}s
     * @param question the {@Question} the {@link Actor} {@link #memorizes}s
     * @return this {@link Actor}
     */
    public Actor memorizes(String name, Question question) {
        memories.put(name, asksFor(question));
        return this;
    }

    /**
     * @param name the name of the memory the {@link Actor} {@link #recites}s
     * @return the memory for the {@link Actor}'s {@link #memories}
     * @throws MissingMemoryException if there's no memory {@link Class} in the
     *                                {@link Actor}'s {@link #memories}
     */
    public <T> T recites(String name) {
        T memory = (T) memories.get(name);
        if (memory == null) {
            throw new MissingMemoryException(this, name);
        }
        return memory;
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