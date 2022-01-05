package org.gaugekit.core;

public class MissingPropertyException extends RuntimeException {

    public MissingPropertyException(String property) {
        super("Property '%s' not resolved".formatted(property));
    }

}
