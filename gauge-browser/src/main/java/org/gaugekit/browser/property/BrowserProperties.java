package org.gaugekit.browser.property;

import org.gaugekit.browser.supplier.BrowserType;

public final class BrowserProperties {

    private BrowserProperties() {
    }

    public static BrowserType getBrowserType() {
        return BrowserType.forName(System.getenv("browser_type"));
    }

    public static String getBrowserWebdriverSupplier() {
        String supplier = System.getenv("browser_webdriver_supplier");
        if (supplier == null) {
            supplier = "local";
        }
        return supplier;
    }

    public static String getBrowserSize() {
        return System.getenv("browser_size");
    }

    public static String getBrowserGridBaseUrl() {
        return System.getenv("browser_grid_base_url");
    }

    public static boolean useBrowserGrid() {
        return getBrowserGridBaseUrl() != null;
    }

    public static Long getBrowserTimeout() {
        return Long.parseLong(System.getenv("browser_timeout"));
    }

    public static boolean isBrowserHeadless() {
        return Boolean.parseBoolean(System.getenv("browser_headless"));
    }

}