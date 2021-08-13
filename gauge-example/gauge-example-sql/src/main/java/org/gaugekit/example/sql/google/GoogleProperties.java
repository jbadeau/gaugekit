package org.gaugekit.example.sql.google;


public final class GoogleProperties {

    private GoogleProperties() {
    }

    public static String getGoogleBaseUrl() {
        return System.getenv("google_base_url");
    }

}