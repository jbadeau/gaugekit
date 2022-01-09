package org.gaugekit.sauron;

import org.aeonbits.owner.Config;
import org.gaugekit.core.property.ProjectPathConverter;

import java.nio.file.Path;

@Config.Sources({"system:env"})
public interface SauronProperties extends Config {

    Boolean sauron_screenshots_enabled();

    @ConverterClass(ProjectPathConverter.class)
    Path sauron_dir();

    @ConverterClass(ProjectPathConverter.class)
    Path sauron_baseline_dir();

    @ConverterClass(ProjectPathConverter.class)
    Path sauron_snapshot_dir();

    @ConverterClass(ProjectPathConverter.class)
    Path sauron_diff_dir();

}