package org.gaugekit.beam.screenplay;

import org.gaugekit.screenplay.Ability;


public class OrchestrateBeamPipelines implements Ability {


    public OrchestrateBeamPipelines() {
    }

    public static Ability runBeamPipelines() {
        return new OrchestrateBeamPipelines();
    }

}
