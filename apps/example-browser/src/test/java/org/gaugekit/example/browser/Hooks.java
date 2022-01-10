package org.gaugekit.example.browser;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.gaugekit.browser.BrowserProperties;
import org.gaugekit.browser.screenplay.BrowseTheWeb;
import org.gaugekit.browser.webdriver.BrowserType;
import org.gaugekit.browser.webdriver.DockerWebDriverSupplier;
import org.gaugekit.core.screenplay.Cast;
import org.gaugekit.core.screenplay.Director;

public class Hooks {

    @BeforeScenario
    public void BeforeScenario() {
        Cast cast = new Cast();
        cast.actorNamed("John", new BrowseTheWeb(new DockerWebDriverSupplier(BrowserType.CHROME)));
        Director.setStage(cast);
    }

    @AfterScenario
    public void afterScenario() {
        Director.drawCurtain();
    }

}
