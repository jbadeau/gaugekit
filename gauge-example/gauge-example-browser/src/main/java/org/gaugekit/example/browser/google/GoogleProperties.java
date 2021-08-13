package org.gaugekit.example.browser.google;


public final class GoogleProperties {

    private GoogleProperties() {
    }

    public static String getGoogleBaseUrl() {
        return System.getenv("google_base_url");
    }

}