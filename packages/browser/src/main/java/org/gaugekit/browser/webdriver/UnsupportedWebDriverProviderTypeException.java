package org.gaugekit.browser.webdriver;

public class UnsupportedWebDriverProviderTypeException extends RuntimeException {

    public UnsupportedWebDriverProviderTypeException(String string) {
        super(String.format("Unknown WebDriver provider type \"%s\"", string));
    }
}
