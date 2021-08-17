package org.gaugekit.screenplay;

import java.util.Arrays;
import java.util.List;

public class ScreenplayProperties {

    private ScreenplayProperties() {
    }

    public static List<String> getPronouns() {
        String pronouns = System.getenv("screenplay_pronouns");

        if (pronouns == null) {
            return Arrays.asList("he", "she", "it", "they");
        }

        return Arrays.asList(pronouns.split(","));
    }

}
