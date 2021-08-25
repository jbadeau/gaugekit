package org.gaugekit.screenplay;

public class MissingMemoryException extends RuntimeException {

    /**
     * @param actor the {@link Actor} missing the memoryClass
     * @param memoryName the name of the missing {@link Memory}
     */
    public MissingMemoryException(Actor actor, String memoryName) {
        super(String.format("%s does not remember %s", actor, memoryName));
    }

}
