package org.gaugekit.browser.screenplay;

import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Task;
import org.openqa.selenium.WebDriver;

public class BrowserTasks {

    private BrowserTasks() {
    }

    public static Task open(String url) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                WebDriver driver = driver(actor);
                driver.get(url);
            }
        };
    }


    private static WebDriver driver(Actor actor) {
        DriveBrowserAbility ability = actor.uses(DriveBrowserAbility.class);
        return ability.getWebDriver();
    }

}
