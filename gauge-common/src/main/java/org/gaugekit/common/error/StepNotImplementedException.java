package org.gaugekit.common.error;

public class StepNotImplementedException extends RuntimeException {

    private static final long serialVersionUID = -3915801189311749818L;

    public StepNotImplementedException(final String msg) {
        super(msg);
    }

    public StepNotImplementedException() {
        super();
    }

}