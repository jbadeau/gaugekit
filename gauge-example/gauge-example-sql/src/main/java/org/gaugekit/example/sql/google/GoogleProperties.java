package org.gaugekit.example.sql.google;


import org.gaugekit.common.property.GaugeProperties;

public final class GoogleProperties extends GaugeProperties {

    private GoogleProperties() {
    }

    public static String getGoogleBaseUrl() {
        return getString("google_base_url");
    }

}