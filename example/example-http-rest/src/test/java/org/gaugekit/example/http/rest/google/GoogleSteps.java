package org.gaugekit.example.http.rest.google;

import com.thoughtworks.gauge.Step;
import org.gaugekit.example.http.rest.common.question.SearchApiQuestions;
import org.gaugekit.example.http.rest.common.task.SearchApiTasks;
import org.gaugekit.example.http.rest.google.search.model.Resource;
import org.gaugekit.screenplay.Actor;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleSteps implements GoogleMemories {

    @Step("And <actor> opens <app>")
    public void open(Actor actor, String app) {
    }

    @Step("When <actor> searches for <term>")
    public void search(Actor actor, String term) {
        actor.attemptsTo(SearchApiTasks.search(term));
        actor.memorizes(RESOURCES, SearchApiQuestions.getResources());
    }

    @Step("Then <actor> verifies that results matches <file>")
    public void verify(Actor actor, Path file) {
        List<Resource> resources = actor.recites(RESOURCES);
        assertThat(resources.size()).isEqualTo(2);
    }

}