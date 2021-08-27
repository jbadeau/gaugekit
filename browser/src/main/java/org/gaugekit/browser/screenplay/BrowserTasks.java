package org.gaugekit.browser.screenplay;

import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Task;
import org.openqa.selenium.WebDriver;

public interface BrowserTasks {

    default Task open(String url) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                WebDriver driver = driver(actor);
                driver.get(url);
            }
        };
    }


    default WebDriver driver(Actor actor) {
        BrowserAbility ability = actor.uses(BrowserAbility.class);
        return ability.getWebDriver();
    }

}
