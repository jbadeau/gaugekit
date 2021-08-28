package org.gaugekit.example.http.graphql.google;


import org.gaugekit.core.GaugeProperties;

public final class GoogleProperties extends GaugeProperties {

    private GoogleProperties() {
    }

    public static String google_base_url() {
        return getString("google_base_url");
    }

}