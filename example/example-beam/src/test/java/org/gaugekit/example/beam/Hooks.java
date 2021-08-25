package org.gaugekit.example.beam;

import com.thoughtworks.gauge.BeforeSuite;
import org.gaugekit.example.beam.common.ability.RenderTemplatesAbility;
import org.gaugekit.example.beam.common.ability.RunBeamPipelinesAbility;
import org.gaugekit.screenplay.Cast;
import org.gaugekit.screenplay.OnStage;

public class Hooks {

    @BeforeSuite
    public void beforeSuite() {
        Cast cast = new Cast();
        cast.actorNamed("John", RenderTemplatesAbility.renderTemplates(), RunBeamPipelinesAbility.runBeamPipelines());
        OnStage.setTheStage(cast);
    }

}
