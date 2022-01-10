package org.gaugekit.example.browser.wikipedia.screenplay;

import org.aeonbits.owner.ConfigCache;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Task;
import org.gaugekit.example.browser.wikipedia.WikipediaProperties;
import org.gaugekit.example.browser.wikipedia.screenplay.interaction.EnterKeys;
import org.gaugekit.example.browser.wikipedia.screenplay.interaction.EnterText;
import org.gaugekit.example.browser.wikipedia.screenplay.interaction.Navigate;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class WikipediaTasks {

    private static final WikipediaProperties WIKIPEDIA_PROPERTIES = ConfigCache.getOrCreate(WikipediaProperties.class);

    private WikipediaTasks() {
    }

    public static Task open(String query) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                actor.attemptsTo(
                        Navigate.to(WIKIPEDIA_PROPERTIES.wikipedia_base_url())
                );
            }

        };
    }

    public static Task query(String query) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                actor.attemptsTo(
                        EnterText.value(query).into(By.name("search")),
                        EnterKeys.value(Keys.ENTER).into(By.name("search"))
                );
            }

        };
    }

}

