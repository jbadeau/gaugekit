package org.gaugekit.browser.supplier;

import org.gaugekit.browser.property.BrowserProperties;

import static java.util.Arrays.stream;


public enum WebDriverSupplierType {

    DOCKER("docker", new DockerWebDriverSupplier(BrowserProperties.browser())),

    REMOTE("remote", new RemoteWebDriverSupplier(BrowserProperties.browser())),

    LOCAL("local", new LocalWebDriverSupplier(BrowserProperties.browser()));

    private final String supplierType;

    private final WebDriverSupplier supplier;

    WebDriverSupplierType(String supplierType, WebDriverSupplier supplier) {
        this.supplierType = supplierType;
        this.supplier = supplier;
    }

    public static WebDriverSupplierType forName(String string) {
        return stream(values())
                .filter(supplierType -> supplierType.name().equalsIgnoreCase(string))
                .findAny()
                .orElseThrow(() -> new UnsupportedWebDriverSupplierTypeException(string));
    }

    public WebDriverSupplier getWebDriverSupplier() {
        return supplier;
    }
}
