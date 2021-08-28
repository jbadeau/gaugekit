package org.gaugekit.browser.screenplay;

import org.gaugekit.browser.webdriver.WebDriverSupplier;
import org.gaugekit.screenplay.Ability;
import org.openqa.selenium.WebDriver;

public class BrowserAbility implements Ability {

    private WebDriverSupplier webDriverSupplier;

    public BrowserAbility(WebDriverSupplier webDriverSupplier) {
        this.webDriverSupplier = webDriverSupplier;
    }

    public static BrowserAbility browser(WebDriverSupplier webDriverSupplier) {
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
