package org.gaugekit.example.browser.wikipedia;


import org.gaugekit.common.property.GaugeProperties;

public final class WikipediaProperties extends GaugeProperties {

    private WikipediaProperties() {
    }

    public static String wikipedia_base_url() {
        return getString("wikipedia_base_url");
    }

}