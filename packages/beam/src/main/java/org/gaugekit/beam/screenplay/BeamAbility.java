package org.gaugekit.beam.screenplay;

import org.apache.beam.sdk.Pipeline;
import org.gaugekit.core.screenplay.Ability;


public class BeamAbility implements Ability {

    public BeamAbility() {
    }

    public static Ability beam() {
        return new BeamAbility();
    }

    public void runPipeline(Pipeline pipeline) {
        pipeline.run();
    }

}
