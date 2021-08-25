package org.gaugekit.common.property;

public class MissingPropertyException extends RuntimeException {

    public MissingPropertyException(String property) {
        super(String.format("Property '%s' not resolved", property));
    }

}
