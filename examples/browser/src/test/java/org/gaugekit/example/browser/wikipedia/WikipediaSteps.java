package org.gaugekit.example.browser.wikipedia;

import com.thoughtworks.gauge.Step;
import org.aeonbits.owner.ConfigCache;
import org.gaugekit.core.screenplay.Actor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.gaugekit.example.browser.wikipedia.screenplay.WikipediaTasks.open;
import static org.gaugekit.example.browser.wikipedia.screenplay.WikipediaTasks.query;
import static org.gaugekit.example.browser.wikipedia.screenplay.WikipediaQuestions.title;

public class WikipediaSteps {

    private static final WikipediaProperties WIKIPEDIA_PROPERTIES = ConfigCache.getOrCreate(WikipediaProperties.class);

    @Step("Given <actor> opens <app>")
    public void openApp(Actor actor, String app) {
        actor.attemptsTo(open(WIKIPEDIA_PROPERTIES.wikipedia_base_url()));
    }

    @Step("When <actor> searches for <term>")
    public void searchFor(Actor actor, String term) {
        actor.attemptsTo(query(term));
    }

    @Step("Then <actor> verifies that <result> is returned")
    public void verifyResult(Actor actor, String result) {
        String title = actor.asksFor(title());
        assertThat(title).contains(result);
    }

}