package org.gaugekit.example.beam.wordcount;

import org.gaugekit.beam.screenplay.BeamAbility;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Task;

public class RunBeamPipeline {

    private RunBeamPipeline() {
    }

    public static Task withOptions(WordCountOptions options) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                BeamAbility ability = actor.uses(BeamAbility.class);;
                ability.runPipeline(WordCount.instanceOf(options));
            }
        };
    }

}
