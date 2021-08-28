package org.gaugekit.example.beam.common.step;

import com.thoughtworks.gauge.Step;
import org.gaugekit.core.screenplay.Actor;

public class CommonSteps {

    @Step("Given actor <actor>")
    public void actor(Actor actor) {
    }

}
