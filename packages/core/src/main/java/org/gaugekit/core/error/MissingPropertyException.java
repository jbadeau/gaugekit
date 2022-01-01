package org.gaugekit.core.error;

public class MissingPropertyException extends RuntimeException {

    public MissingPropertyException(String property) {
        super("Property '%s' not resolved".formatted(property));
    }

}
