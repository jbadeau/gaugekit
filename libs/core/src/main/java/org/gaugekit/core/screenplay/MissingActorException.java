package org.gaugekit.core.screenplay;

public class MissingActorException extends RuntimeException {

    public MissingActorException(String actor) {
        super(String.format("missing actor named \"%s\" in the cast", actor));
    }

}
