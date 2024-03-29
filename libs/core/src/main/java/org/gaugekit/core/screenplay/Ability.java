package org.gaugekit.core.screenplay;

/**
 * An {@link Ability} can be {@link Actor#uses used} by an {@link Actor} to {@link Actor#attemptsTo perform} a
 * {@link Task} or {@link Actor#asksFor answer} a {@link Question}.
 */
public interface Ability {

    default void cleanUp() {
    }

}
