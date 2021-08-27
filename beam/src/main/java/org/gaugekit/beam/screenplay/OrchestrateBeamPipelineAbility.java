package org.gaugekit.beam.screenplay;

import org.gaugekit.screenplay.Ability;


public class OrchestrateBeamPipelineAbility implements Ability {


    public OrchestrateBeamPipelineAbility() {
    }

    public static Ability orchestrateBeamPipeline() {
        return new OrchestrateBeamPipelineAbility();
    }

}
