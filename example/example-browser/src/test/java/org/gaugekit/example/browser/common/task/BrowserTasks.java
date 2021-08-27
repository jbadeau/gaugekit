package org.gaugekit.example.browser.common.task;

import org.gaugekit.example.browser.common.ability.SurfTheWebAbility;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Task;

public class BrowserTasks {

    private BrowserTasks() {
    }

    public static Task openApp(String url) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                SurfTheWebAbility ability = actor.uses(SurfTheWebAbility.class);
                ability.getWebDriver().get(url);
            }
        };
    }

}
