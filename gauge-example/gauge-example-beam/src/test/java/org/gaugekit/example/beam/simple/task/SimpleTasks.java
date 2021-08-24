package org.gaugekit.example.beam.simple.task;

import org.gaugekit.example.beam.common.ability.RunBeamPipelines;
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
                RunBeamPipelines ability = actor.uses(RunBeamPipelines.class);
                SimplePipeline.runSimplePipeline(options);
            }
        };
    }

}
