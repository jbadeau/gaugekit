package org.gaugekit.browser.screenplay;

import org.gaugekit.browser.webdriver.WebDriverProvider;
import org.gaugekit.core.screenplay.Ability;
import org.openqa.selenium.WebDriver;

public class BrowserAbility implements Ability {

    private WebDriverProvider webDriverSupplier;

    public BrowserAbility(WebDriverProvider webDriverSupplier) {
        this.webDriverSupplier = webDriverSupplier;
    }

    public static BrowserAbility browser(WebDriverProvider webDriverSupplier) {
        return new BrowserAbility(webDriverSupplier);
    }

    public WebDriver getWebDriver() {
        return webDriverSupplier.get();
    }

    @Override
    public void cleanUp() {
        try {
            webDriverSupplier.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return String.format("Surfing the web using %s", webDriverSupplier);
    }

}
