package org.gaugekit.browser.webdriver;

import org.gaugekit.browser.BrowserType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.util.function.Supplier;

/**
 * A {@link Supplier} for {@link WebDriver}, which also takes care of {@link WebDriver#quit() quitting} the
 * {@link WebDriver}.
 */
public abstract class WebDriverProvider implements Supplier<WebDriver>, AutoCloseable {

    private final BrowserType browserType;
    private final Capabilities capabilities;

    /**
     * @param browserType            the {@link BrowserType} to be setup
     * @param additionalCapabilities additional {@link Capabilities} for the {@link WebDriver}
     */
    protected WebDriverProvider(BrowserType browserType, Capabilities additionalCapabilities) {
        this.browserType = browserType;
        this.capabilities = additionalCapabilities == null ?
                browserType.getBaseCapabilities() :
                browserType.getBaseCapabilities().merge(additionalCapabilities);
    }

    /**
     * @return the {@link BrowserType}
     */
    public BrowserType getBrowserType() {
        return browserType;
    }

    /**
     * @return {@link Capabilities} for the {@link WebDriver}, merged from the
     * {@link BrowserType#getBaseCapabilities BrowserType's baseCapabilities} and additional capabilities.
     */
    public Capabilities getCapabilities() {
        return capabilities;
    }

    @Override
    public String toString() {
        return browserType.name();
    }
}