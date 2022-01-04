package org.gaugekit.core;

public class StepNotImplementedException extends RuntimeException {

    public StepNotImplementedException(final String msg) {
        super(msg);
    }

    public StepNotImplementedException() {
        super();
    }

}