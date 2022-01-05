package org.gaugekit.core;

import org.aeonbits.owner.Config;

@Config.Sources({"system:env"})
public interface ScreenplayProperties extends Config {

    /**
     *
     */
    String screenplay_pronouns();

}
