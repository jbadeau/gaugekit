package org.gaugekit.example.browser.wikipedia;

import com.thoughtworks.gauge.Step;
import org.gaugekit.example.browser.common.question.BrowserQuestions;
import org.gaugekit.screenplay.Actor;

import static org.gaugekit.example.browser.wikipedia.WikipediaProperties.*;
import static org.gaugekit.example.browser.wikipedia.WikipediaTasks.*;
import static org.gaugekit.example.browser.common.task.BrowserTasks.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WikipediaSteps {

    @Step("And <actor> opens <app>")
    public void open(Actor actor, String app) {
        actor.attemptsTo(openApp(wikipedia_base_url()));
    }

    @Step("When <actor> searches for <term>")
    public void search(Actor actor, String term) {
        actor.attemptsTo(searchFor(term));
    }

    @Step("Then <actor> verifies that <result> is returned")
    public void verify(Actor actor, String result) {
        String title = actor.asksFor(BrowserQuestions.title());
        assertThat(title).contains(result);
    }

}