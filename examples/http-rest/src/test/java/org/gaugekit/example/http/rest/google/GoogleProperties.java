package org.gaugekit.example.http.rest.google;


import org.gaugekit.core.DefaultProperties;

public final class GoogleProperties extends DefaultProperties {

    private GoogleProperties() {
    }

    public static String google_base_url() {
        return getString("google_base_url");
    }

}