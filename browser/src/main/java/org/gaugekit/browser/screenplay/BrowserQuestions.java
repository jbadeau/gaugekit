package org.gaugekit.browser.screenplay;

import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Question;
import org.openqa.selenium.WebDriver;

public class BrowserQuestions {

    private BrowserQuestions() {
    }

    public static Question<String> title() {
        return new Question<String>() {
            @Override
            public String answerAs(Actor actor) {
                DriveBrowserAbility ability = actor.uses(DriveBrowserAbility.class);
                WebDriver driver = ability.getWebDriver();
                return driver.getTitle();
            }
        };
    }

}
