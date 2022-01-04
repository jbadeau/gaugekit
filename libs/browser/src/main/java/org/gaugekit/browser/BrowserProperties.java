package org.gaugekit.browser;

import org.gaugekit.browser.webdriver.BrowserType;
import org.gaugekit.core.DefaultProperties;

public final class BrowserProperties extends DefaultProperties {

    private static final String browser = "browser";

    private static final String browser_webdriver_supplier = "browser_webdriver_supplier";

    private BrowserProperties() {
    }

    public static BrowserType browser() {
        return BrowserType.forName(getString(browser));
    }

}