package org.gaugekit.screenplay;

/**
 * A {@link RetryInterruptedException} is thrown when an {@link Actor} get interrupted while retrying a
 * {@link Retryable}.
 *
 * @see Actor#attemptsTo(RetryableTask)
 * @see Actor#asksFor(RetryableQuestion)
 */
public class RetryInterruptedException extends RuntimeException {

    /**
     * @param actor     the interrupted {@link Actor}
     * @param retryable the {@link Retryable}
     * @param cause     the {@link InterruptedException} which caused the interruption
     */
    public RetryInterruptedException(Actor actor, Retryable retryable, InterruptedException cause) {
        super(String.format("%s was interrupted while retrying %s", actor, retryable), cause);
    }
}
