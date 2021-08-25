package org.gaugekit.example.browser.common.question;

import org.gaugekit.example.browser.common.ability.SurfTheWebAbility;
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
                SurfTheWebAbility ability = actor.uses(SurfTheWebAbility.class);
                WebDriver driver = ability.getWebDriver();
                return driver.getTitle();
            }
        };
    }

}
