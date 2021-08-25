package org.gaugekit.browser.supplier;

public class UnsupportedWebDriverSupplierTypeException extends RuntimeException {

    public UnsupportedWebDriverSupplierTypeException(String string) {
        super(String.format("Unknown WebDriver supplier type \"%s\"", string));
    }
}
