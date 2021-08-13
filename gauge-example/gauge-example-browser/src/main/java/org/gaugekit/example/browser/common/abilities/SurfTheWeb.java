package org.gaugekit.example.browser.common.abilities;

import com.codeborne.selenide.Selenide;
import org.gaugekit.browser.property.BrowserProperties;
import org.gaugekit.browser.supplier.DockerWebDriverSupplier;
import org.gaugekit.browser.supplier.LocalWebDriverSupplier;
import org.gaugekit.browser.supplier.WebDriverSupplier;
import org.gaugekit.screenplay.Ability;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

/**
 * {@link Ability} to browse the web using a Selenium {@link WebDriver}.
 */
public class SurfTheWeb implements Ability, AutoCloseable {

    private WebDriverSupplier webDriverSupplier;

    private Selenide selenide;

    public SurfTheWeb() {
        String supplier = BrowserProperties.getBrowserWebdriverSupplier();
        switch (supplier) {
            case "docker":
                this.webDriverSupplier = new DockerWebDriverSupplier(BrowserProperties.getBrowserType());
                setWebDriver(this.webDriverSupplier.get());
            case "remote":
                this.webDriverSupplier = new DockerWebDriverSupplier(BrowserProperties.getBrowserType());
                setWebDriver(this.webDriverSupplier.get());
            case "local":
                this.webDriverSupplier = new LocalWebDriverSupplier(BrowserProperties.getBrowserType());
                setWebDriver(this.webDriverSupplier.get());
        }
        if (webDriverSupplier == null) {
            throw new RuntimeException(String.format("Unknown webdriver supplier \"%s\"", supplier));
        }
        this.selenide = new Selenide();
    }

    public static Ability browseTheWeb() {
        return new SurfTheWeb();
    }

    /**
     * @return a {@link WebDriver} instance
     */
    public Selenide getBrowser() {
        return this.selenide;
    }

    @Override
    public void close() throws Exception {
        this.selenide.closeWebDriver();
    }

    @Override
    public void cleanUp() {
        getBrowser().closeWebDriver();
    }

    @Override
    public String toString() {
        return String.format("browse the web using %s", webDriverSupplier);
    }

}
