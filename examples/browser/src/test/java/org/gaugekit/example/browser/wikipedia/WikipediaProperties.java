package org.gaugekit.example.browser.wikipedia;


import org.gaugekit.core.DefaultProperties;

public final class WikipediaProperties extends DefaultProperties {

    private WikipediaProperties() {
    }

    public static String baseUrl() {
        return getString("wikipedia_base_url");
    }

}