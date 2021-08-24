package org.gaugekit.screenplay;

/**
 * A {@link Task} can be {@link Actor#attemptsTo performed} by an {@link Actor}.
 */
public interface Task {

    /**
     * Perform this {@link Task} as the given {@link Actor}.
     *
     * @param actor the {@link Actor} to perform this.
     */
    void performAs(Actor actor);
}
