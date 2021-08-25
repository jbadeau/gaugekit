package org.gaugekit.screenplay.property;

import org.gaugekit.common.property.GaugeProperties;

import java.util.List;

public final class ScreenplayProperties extends GaugeProperties {

    private static final String screenplay_pronouns = "screenplay_pronouns";

    private ScreenplayProperties() {
        super();
    }

    public static List<String> screenplay_pronouns() {
        return getStringList(screenplay_pronouns, ",");
    }

}
