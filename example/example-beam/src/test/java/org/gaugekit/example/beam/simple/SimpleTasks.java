package org.gaugekit.example.beam.simple;

import org.gaugekit.beam.screenplay.BeamAbility;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Task;

public class
SimpleTasks {

    private SimpleTasks() {
    }

    public static Task runSimplePipeline(SimplePipelineOptions options) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                BeamAbility ability = actor.uses(BeamAbility.class);
                SimplePipeline.runSimplePipeline(options);
            }
        };
    }

}
