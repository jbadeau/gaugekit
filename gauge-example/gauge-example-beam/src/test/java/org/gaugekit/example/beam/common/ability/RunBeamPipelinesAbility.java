package org.gaugekit.example.beam.common.ability;

import org.gaugekit.screenplay.Ability;


public class RunBeamPipelinesAbility implements Ability {


    public RunBeamPipelinesAbility() {
    }

    public static Ability runBeamPipelines() {
        return new RunBeamPipelinesAbility();
    }

}
