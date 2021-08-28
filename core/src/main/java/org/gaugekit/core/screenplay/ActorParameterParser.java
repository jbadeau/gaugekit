package org.gaugekit.core.screenplay;

import com.thoughtworks.gauge.execution.parameters.parsers.base.CustomParameterParser;
import gauge.messages.Spec;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Director;

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
