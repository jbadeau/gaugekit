package org.gaugekit.screenplay.parameter;

import com.thoughtworks.gauge.execution.parameters.parsers.base.CustomParameterParser;
import gauge.messages.Spec;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Director;

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
