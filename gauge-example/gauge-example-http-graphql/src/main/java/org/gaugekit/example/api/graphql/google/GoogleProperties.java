package org.gaugekit.example.api.graphql.google;


public final class GoogleProperties {

    private GoogleProperties() {
    }

    public static String getGoogleBaseUrl() {
        return System.getenv("google_base_url");
    }

}