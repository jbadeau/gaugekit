package org.gaugekit.screenplay;

/**
 * An {@link Ability} can be {@link Actor#uses used} by an {@link Actor} to {@link Actor#attemptTo perform} a
 * {@link Task} or {@link Actor#askFor answer} a {@link Question}.
 */
public interface Ability {

    default void cleanUp() {
    }

}
