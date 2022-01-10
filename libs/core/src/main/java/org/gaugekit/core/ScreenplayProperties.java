package org.gaugekit.core;

import org.aeonbits.owner.Config;

import java.util.List;

@Config.Sources({"system:env"})
public interface ScreenplayProperties extends Config {

    /**
     *
     */
    @Separator(",")
    List<String> screenplay_pronouns();

}
