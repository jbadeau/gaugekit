package org.gaugekit.example.browser.common.steps;

import org.gaugekit.example.browser.common.abilities.SurfTheWeb;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Cast;
import com.thoughtworks.gauge.Step;


public class CommonSteps {

    @Step("actor <actor>")
    public void createActor(Actor actor) {
        Cast.addActor(actor);
        Cast.getActorInSpotlight().can(SurfTheWeb.browseTheWeb());
    }

}
