package org.gaugekit.example.browser.wikipedia;

import com.thoughtworks.gauge.Step;
import org.gaugekit.browser.screenplay.BrowserQuestions;
import org.gaugekit.browser.screenplay.BrowserTasks;
import org.gaugekit.core.screenplay.Actor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.gaugekit.example.browser.wikipedia.WikipediaProperties.baseUrl;
import static org.gaugekit.example.browser.wikipedia.WikipediaTasks.searchFor;

public class WikipediaSteps implements BrowserTasks, BrowserQuestions {

    @Step("Given <actor> opens <app>")
    public void open(Actor actor, String app) {
        actor.attemptsTo(open(baseUrl()));
    }

    @Step("When <actor> searches for <term>")
    public void search(Actor actor, String term) {
        actor.attemptsTo(searchFor(term));
    }

    @Step("Then <actor> verifies that <result> is returned")
    public void verify(Actor actor, String result) {
        String title = actor.asksFor(title());
        assertThat(title).contains(result);
    }

}