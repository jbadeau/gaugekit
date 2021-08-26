package org.gaugekit.example.browser.common.ability;

import org.gaugekit.browser.WebDriverSupplier;
import org.gaugekit.screenplay.Ability;
import org.openqa.selenium.WebDriver;


public class SurfTheWebAbility implements Ability {

    private WebDriverSupplier webDriverSupplier;

    public SurfTheWebAbility(WebDriverSupplier webDriverSupplier) {
        this.webDriverSupplier = webDriverSupplier;
    }

    public static SurfTheWebAbility surfTheWeb(WebDriverSupplier webDriverSupplier) {
        return new SurfTheWebAbility(webDriverSupplier);
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
