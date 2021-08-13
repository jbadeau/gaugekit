package org.gaugekit.example.api.rest.google;


public final class GoogleProperties {

    private GoogleProperties() {
    }

    public static String getGoogleBaseUrl() {
        return System.getenv("google_base_url");
    }

}