package org.gaugekit.example.browser.wikipedia;

import com.thoughtworks.gauge.Step;
import org.gaugekit.example.browser.common.question.BrowserQuestions;
import org.gaugekit.example.browser.common.task.BrowserTasks;
import org.gaugekit.screenplay.Actor;

import static org.assertj.core.api.Assertions.assertThat;

public class WikipediaSteps {

    @Step("And <actor> opens <app>")
    public void openGoogle(Actor actor, String app) {
        actor.attemptsTo(BrowserTasks.open(WikipediaProperties.wikipedia_base_url()));
    }

    @Step("When <actor> searches for <query>")
    public void search(Actor actor, String query) {
        actor.attemptsTo(WikipediaTasks.search(query));
    }

    @Step("Then <actor> verifies that <result> is returned")
    public void results(Actor actor, String result) {
        String title = actor.asksFor(BrowserQuestions.title());
        assertThat(title).contains(result);
    }

}