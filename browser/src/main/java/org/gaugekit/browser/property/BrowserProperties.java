package org.gaugekit.browser.property;

import org.gaugekit.browser.supplier.BrowserType;
import org.gaugekit.browser.supplier.WebDriverSupplier;
import org.gaugekit.browser.supplier.WebDriverSupplierType;
import org.gaugekit.common.property.GaugeProperties;

public final class BrowserProperties extends GaugeProperties {

    private static final String browser = "browser";

    private static final String browser_webdriver_supplier = "browser_webdriver_supplier";

    private BrowserProperties() {
    }

    public static BrowserType browser() {
        return BrowserType.forName(getString(browser));
    }

    public static WebDriverSupplier browser_webdriver_supplier() {
        return WebDriverSupplierType.forName(getString(browser_webdriver_supplier)).getWebDriverSupplier();
    }

}