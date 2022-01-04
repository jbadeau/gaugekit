package org.gaugekit.core.screenplay;

import com.thoughtworks.gauge.execution.parameters.parsers.base.CustomParameterParser;
import gauge.messages.Spec;

public class ActorParameterParser extends CustomParameterParser<Actor> {

    @Override
    protected Actor customParse(Class clazz, Spec.Parameter parameter) {
        return Director.actorCalled(parameter.getValue());
    }

    @Override
    public boolean canParse(Class<?> clazz, Spec.Parameter parameter) {
        return clazz == Actor.class;
    }

}
