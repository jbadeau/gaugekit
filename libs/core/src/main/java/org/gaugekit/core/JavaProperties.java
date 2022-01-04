package org.gaugekit.core;


import org.aeonbits.owner.Config;

import java.nio.file.Path;

@Config.Sources({"system:env"})
public interface JavaProperties extends Config {

    /**
     * Specify an alternate Java home if you want to use a custom version.
     */
    Path gauge_java_home();

    /**
     * Specify the directory where additional libs are kept you can specify multiple directory names separated with a comma (,).
     */
    String gauge_additional_libs();

    /**
     * JVM argument passed to java while launching.
     */
    String gauge_jvm_args();

    /**
     * Specify the directory containing java files to be compiled you can specify multiple directory names separated with a comma (,).
     */
    Path gauge_custom_compile_dir();

    /**
     * Specify the level at which the objects should be cleared Possible values are suite, spec and scenario. Default value is suite.
     */
    String gauge_clear_state_level();

}