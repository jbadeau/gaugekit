package org.gaugekit.browser.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.gaugekit.browser.BrowserType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

/**
 * A {@link WebDriverProvider} for locally installed browser instances. Uses {@link WebDriverManager} to setup the local
 * binary.
 */
public class LocalWebDriverProvider extends WebDriverProvider {

    private WebDriver webDriver;

    /**
     * @param browserType            the {@link BrowserType} to be setup
     * @param additionalCapabilities additional {@link Capabilities} for the {@link WebDriver}
     */
    public LocalWebDriverProvider(BrowserType browserType, Capabilities additionalCapabilities) {
        super(browserType, additionalCapabilities);
    }

    /**
     * @param browserType the {@link BrowserType} to be setup
     */
    public LocalWebDriverProvider(BrowserType browserType) {
        this(browserType, null);
    }

    @Override
    public WebDriver get() {
        if (webDriver == null) {
            try {
                WebDriverManager.getInstance(getBrowserType().getWebDriverClass()).setup();
                webDriver = getBrowserType().getWebDriverClass().getConstructor(Capabilities.class)
                        .newInstance(getCapabilities());
            } catch (Exception e) {
                throw new WebDriverProviderSetupFailedException(e);
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