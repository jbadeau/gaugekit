package org.gaugekit.browser;

import org.gaugekit.browser.webdriver.WebDriverProvider;
import org.gaugekit.browser.webdriver.WebDriverProviderType;
import org.gaugekit.core.GaugeProperties;

public final class BrowserProperties extends GaugeProperties {

    private static final String browser = "browser";

    private static final String browser_webdriver_supplier = "browser_webdriver_supplier";

    private BrowserProperties() {
    }

    public static BrowserType browser() {
        return BrowserType.forName(getString(browser));
    }

    public static WebDriverProvider webdriverSupplier() {
        return WebDriverProviderType.forName(getString(browser_webdriver_supplier)).getWebDriverSupplier();
    }

}