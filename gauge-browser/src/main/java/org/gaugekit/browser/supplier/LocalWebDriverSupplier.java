package org.gaugekit.browser.supplier;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public class LocalWebDriverSupplier extends WebDriverSupplier {

    private WebDriver webDriver;

    /**
     * @param browserType            the {@link BrowserType} to be setup
     * @param additionalCapabilities additional {@link Capabilities} for the {@link WebDriver}
     */
    public LocalWebDriverSupplier(BrowserType browserType, Capabilities additionalCapabilities) {
        super(browserType, additionalCapabilities);
    }

    /**
     * @param browserType the {@link BrowserType} to be setup
     */
    public LocalWebDriverSupplier(BrowserType browserType) {
        this(browserType, null);
    }

    @Override
    public WebDriver get() {
        if (webDriver == null) {
            try {
                webDriver = getBrowserType().getWebDriverClass().getConstructor(Capabilities.class).newInstance(getCapabilities());
            } catch (Exception e) {
                throw new WebDriverSetupFailedException(e);
            }
        }
        return webDriver;
    }

    @Override
    public void close() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
