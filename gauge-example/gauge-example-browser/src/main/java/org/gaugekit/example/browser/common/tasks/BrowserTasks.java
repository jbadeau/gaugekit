package org.gaugekit.example.browser.common.tasks;

import org.gaugekit.example.browser.common.abilities.SurfTheWeb;
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
                ability.getBrowser().open(url);
            }
        };
    }

}
