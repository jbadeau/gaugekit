package org.gaugekit.example.beam;

import com.thoughtworks.gauge.BeforeSuite;
import org.gaugekit.example.beam.common.ability.RenderTemplates;
import org.gaugekit.example.beam.common.ability.RunBeamPipelines;
import org.gaugekit.screenplay.Cast;
import org.gaugekit.screenplay.OnStage;

public class Hooks {

    @BeforeSuite
    public void beforeSuite() {
        Cast cast = new Cast();
        cast.actorNamed("John", RenderTemplates.renderTemplates(), RunBeamPipelines.runBeamPipelines());
        OnStage.setTheStage(cast);
    }

}
