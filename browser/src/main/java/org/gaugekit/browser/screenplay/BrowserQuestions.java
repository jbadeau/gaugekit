package org.gaugekit.browser.screenplay;

import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Question;
import org.openqa.selenium.WebDriver;

public interface BrowserQuestions {

    default Question<String> title() {
        return new Question<String>() {
            @Override
            public String answerAs(Actor actor) {
                BrowserAbility ability = actor.uses(BrowserAbility.class);
                WebDriver driver = ability.getWebDriver();
                return driver.getTitle();
            }
        };
    }

}
