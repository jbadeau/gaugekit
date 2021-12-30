package org.gaugekit.browser.screenplay;

import org.gaugekit.browser.webdriver.WebDriverSupplier;
import org.gaugekit.core.screenplay.Ability;
import org.openqa.selenium.WebDriver;

import static java.util.Objects.requireNonNull;

/**
 * {@link org.gaugekit.core.screenplay.Ability} to browse the web using a Selenium {@link WebDriver}.
 */
public record BrowseTheWeb(WebDriverSupplier webDriverSupplier) implements Ability, AutoCloseable {

    /**
     * @param webDriverSupplier the {@link WebDriverSupplier} used to setup the {@link WebDriver}
     */
    public BrowseTheWeb {
        requireNonNull(webDriverSupplier);
    }

    /**
     * @return a {@link WebDriver} instance
     */
    public WebDriver getWebDriver() {
        return webDriverSupplier.get();
    }

    @Override
    public void close() throws Exception {
        webDriverSupplier.close();
    }

    @Override
    public String toString() {
        return "browse the web using %s".formatted(webDriverSupplier);
    }

}