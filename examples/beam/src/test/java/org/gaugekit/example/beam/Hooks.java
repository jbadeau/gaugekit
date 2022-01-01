package org.gaugekit.example.beam;

import com.thoughtworks.gauge.BeforeSuite;
import org.gaugekit.core.file.FileAbility;
import org.gaugekit.core.table.TableAbility;
import org.gaugekit.template.screenplay.TemplateAbility;
import org.gaugekit.beam.screenplay.BeamAbility;
import org.gaugekit.core.screenplay.Cast;
import org.gaugekit.core.screenplay.Director;

public class Hooks {

    @BeforeSuite
    public void beforeSuite() {
        Cast cast = new Cast();
        cast.actorNamed("John",
                TableAbility.table(),
                FileAbility.io(),
                TemplateAbility.template(),
                BeamAbility.beam());
        Director.setStage(cast);
    }

}
