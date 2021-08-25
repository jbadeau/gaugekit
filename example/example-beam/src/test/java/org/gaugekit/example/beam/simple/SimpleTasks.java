package org.gaugekit.example.beam.simple;

import org.gaugekit.example.beam.common.ability.RunBeamPipelinesAbility;
import org.gaugekit.example.beam.simple.SimplePipeline;
import org.gaugekit.example.beam.simple.SimplePipelineOptions;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Task;

public class SimpleTasks {

    private SimpleTasks() {
    }

    public static Task runSimplePipeline(SimplePipelineOptions options) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                RunBeamPipelinesAbility ability = actor.uses(RunBeamPipelinesAbility.class);
                SimplePipeline.runSimplePipeline(options);
            }
        };
    }

}
