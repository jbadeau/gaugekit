package org.gaugekit.example.beam;

import com.thoughtworks.gauge.BeforeSuite;
import org.gaugekit.template.screenplay.TemplateAbility;
import org.gaugekit.beam.screenplay.OrchestrateBeamPipelineAbility;
import org.gaugekit.screenplay.Cast;
import org.gaugekit.screenplay.Director;

public class Hooks {

    @BeforeSuite
    public void beforeSuite() {
        Cast cast = new Cast();
        cast.actorNamed("John", TemplateAbility.template(), OrchestrateBeamPipelineAbility.orchestrateBeamPipeline());
        Director.setStage(cast);
    }

}
