package org.gaugekit.browser.webdriver;

/**
 * {@link RuntimeException} to be thrown when a {@link WebDriverProvider} failed to setup the
 * {@link org.openqa.selenium.WebDriver}.
 */
public class WebDriverProviderSetupFailedException extends RuntimeException {

    /**
     * @param cause the causing {@link Exception}
     */
    public WebDriverProviderSetupFailedException(Exception cause) {
        super(cause);
    }
}
