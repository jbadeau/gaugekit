package org.gaugekit.example.browser.common.task;

import org.gaugekit.example.browser.common.ability.SurfTheWeb;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Task;

public class BrowserTasks {

    private BrowserTasks() {
    }

    public static Task open(String url) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                SurfTheWeb ability = actor.uses(SurfTheWeb.class);
                ability.getWebDriver().get(url);
            }
        };
    }

}
