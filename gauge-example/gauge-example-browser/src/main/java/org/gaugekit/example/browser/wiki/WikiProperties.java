package org.gaugekit.example.browser.wiki;

public final class WikiProperties {

    private WikiProperties() {
    }

    public static String getWikiBaseUrl() {
        return System.getenv("wiki_base_url");
    }

}