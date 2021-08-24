package org.gaugekit.example.beam.common.ability;

import org.gaugekit.screenplay.Ability;


public class RunBeamPipelines implements Ability {


    public RunBeamPipelines() {
    }

    public static Ability runBeamPipelines() {
        return new RunBeamPipelines();
    }

}
