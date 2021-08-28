package org.gaugekit.core.screenplay;

import org.gaugekit.core.GaugeProperties;

import java.util.List;

public final class ScreenplayProperties extends GaugeProperties {

    private static final String screenplay_pronouns = "screenplay_pronouns";

    private ScreenplayProperties() {
        super();
    }

    public static List<String> pronouns() {
        return getStringList(screenplay_pronouns, ",");
    }

}
