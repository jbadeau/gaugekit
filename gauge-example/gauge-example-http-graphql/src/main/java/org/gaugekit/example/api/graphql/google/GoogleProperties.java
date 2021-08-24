package org.gaugekit.example.api.graphql.google;


import org.gaugekit.common.property.GaugeProperties;

public final class GoogleProperties extends GaugeProperties {

    private GoogleProperties() {
    }

    public static String google_base_url() {
        return getString("google_base_url");
    }

}