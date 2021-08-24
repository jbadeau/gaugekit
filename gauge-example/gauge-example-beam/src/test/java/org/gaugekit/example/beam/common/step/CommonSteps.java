package org.gaugekit.example.beam.common.step;

import com.thoughtworks.gauge.Step;
import org.gaugekit.example.beam.simple.SimpleMemories;
import org.gaugekit.screenplay.Actor;

public class CommonSteps implements SimpleMemories {

    @Step("Given actor <Mark>")
    public void actor(Actor actor) {
    }

}
