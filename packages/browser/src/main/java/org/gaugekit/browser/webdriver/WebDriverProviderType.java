package org.gaugekit.browser.webdriver;

import org.gaugekit.browser.BrowserProperties;

import static java.util.Arrays.stream;


public enum WebDriverProviderType {

    DOCKER("docker", new DockerWebDriverProvider(BrowserProperties.browser())),

    LOCAL("local", new LocalWebDriverProvider(BrowserProperties.browser()));

    private final String supplierType;

    private final WebDriverProvider supplier;

    WebDriverProviderType(String supplierType, WebDriverProvider supplier) {
        this.supplierType = supplierType;
        this.supplier = supplier;
    }

    public static WebDriverProviderType forName(String string) {
        return stream(values())
                .filter(supplierType -> supplierType.name().equalsIgnoreCase(string))
                .findAny()
                .orElseThrow(() -> new UnsupportedWebDriverProviderTypeException(string));
    }

    public WebDriverProvider getWebDriverSupplier() {
        return supplier;
    }
}
