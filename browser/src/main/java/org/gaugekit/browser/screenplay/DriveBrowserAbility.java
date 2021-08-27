package org.gaugekit.browser.screenplay;

import org.gaugekit.browser.webdriver.WebDriverSupplier;
import org.gaugekit.screenplay.Ability;
import org.openqa.selenium.WebDriver;

public class DriveBrowserAbility implements Ability {

    private WebDriverSupplier webDriverSupplier;

    public DriveBrowserAbility(WebDriverSupplier webDriverSupplier) {
        this.webDriverSupplier = webDriverSupplier;
    }

    public static DriveBrowserAbility driveBrowser(WebDriverSupplier webDriverSupplier) {
        return new DriveBrowserAbility(webDriverSupplier);
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
