package org.gaugekit.example.beam.common.abilities;

import org.gaugekit.screenplay.Ability;


public class RunBeamPipelines implements Ability {


    public RunBeamPipelines() {
    }

    public static Ability runBeamPipelines() {
        return new RunBeamPipelines();
    }

}
