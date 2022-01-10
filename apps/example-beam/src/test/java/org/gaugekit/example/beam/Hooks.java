package org.gaugekit.example.beam;

import com.thoughtworks.gauge.BeforeSuite;
import org.gaugekit.beam.screenplay.RunBeamPipelines;
import org.gaugekit.core.io.file.screenplay.ManageFiles;
import org.gaugekit.core.screenplay.Cast;
import org.gaugekit.core.screenplay.Director;
import org.gaugekit.core.table.screenplay.ManageTables;
import org.gaugekit.template.screenplay.RenderTemplates;

public class Hooks {

    @BeforeSuite
    public void beforeSuite() {
        Cast cast = new Cast();

        cast.actorNamed(
                "John", ManageTables.with(),
                new ManageFiles(),
                new RenderTemplates(),
                new RunBeamPipelines());

        Director.setStage(cast);
    }

}
