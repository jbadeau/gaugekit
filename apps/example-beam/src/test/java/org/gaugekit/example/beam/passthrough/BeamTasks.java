package org.gaugekit.example.beam.passthrough;

import org.apache.beam.sdk.Pipeline;
import org.gaugekit.beam.screenplay.RunBeamPipelines;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Task;

public class BeamTasks {

    private BeamTasks() {
    }

    public static Task runBeamPipeline(Pipeline pipeline) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                RunBeamPipelines ability = actor.uses(RunBeamPipelines.class);;
                ability.runPipeline(pipeline);
            }
        };
    }

}
