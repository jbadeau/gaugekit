package org.gaugekit.example.browser.wikipedia.screenplay;

import org.gaugekit.browser.screenplay.BrowseTheWeb;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Question;
import org.openqa.selenium.WebDriver;

public class WikipediaQuestions {


    public static  Question<String> title() {
        return new Question<String>() {
            @Override
            public String answerAs(Actor actor) {
                BrowseTheWeb ability = actor.uses(BrowseTheWeb.class);
                WebDriver driver = ability.getWebDriver();
                return driver.getTitle();
            }
        };
    }

}
