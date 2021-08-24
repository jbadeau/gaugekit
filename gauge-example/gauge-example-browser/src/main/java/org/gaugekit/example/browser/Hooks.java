package org.gaugekit.example.browser;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.gaugekit.browser.property.BrowserProperties;
import org.gaugekit.example.browser.common.ability.SurfTheWeb;
import org.gaugekit.screenplay.Cast;
import org.gaugekit.screenplay.OnStage;

public class Hooks {

    @BeforeScenario
    public void BeforeScenario() {
        Cast cast = new Cast();
        cast.actorNamed("John", SurfTheWeb.surfTheWeb(BrowserProperties.browser_webdriver_supplier()));
        OnStage.setTheStage(cast);
    }

    @AfterScenario
    public void afterScenario() {
        OnStage.drawCurtain();
    }

}
