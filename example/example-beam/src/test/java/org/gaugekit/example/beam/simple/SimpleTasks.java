package org.gaugekit.example.beam.simple;

import org.gaugekit.beam.screenplay.OrchestrateBeamPipelines;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Task;

public class SimpleTasks {

    private SimpleTasks() {
    }

    public static Task runSimplePipeline(SimplePipelineOptions options) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                OrchestrateBeamPipelines ability = actor.uses(OrchestrateBeamPipelines.class);
                SimplePipeline.runSimplePipeline(options);
            }
        };
    }

}
