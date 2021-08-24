package org.gaugekit.example.browser.common.ability;

import org.gaugekit.browser.supplier.WebDriverSupplier;
import org.gaugekit.screenplay.Ability;
import org.openqa.selenium.WebDriver;


public class SurfTheWeb implements Ability {

    private WebDriverSupplier webDriverSupplier;

    public SurfTheWeb(WebDriverSupplier webDriverSupplier) {
        this.webDriverSupplier = webDriverSupplier;
    }

    public static SurfTheWeb surfTheWeb(WebDriverSupplier webDriverSupplier) {
        return new SurfTheWeb(webDriverSupplier);
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
        return String.format("Browse the web using %s", webDriverSupplier);
    }

}
