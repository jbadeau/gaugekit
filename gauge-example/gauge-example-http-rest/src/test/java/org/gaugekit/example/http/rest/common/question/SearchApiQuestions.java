package org.gaugekit.example.http.rest.common.question;

import org.gaugekit.example.http.rest.common.ability.SearchApiAbility;
import org.gaugekit.example.http.rest.google.search.model.Resource;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Question;

import java.util.List;

public class SearchApiQuestions {

    private SearchApiQuestions() {
    }

    public static Question<List<Resource>> getResources() {
        return new Question<List<Resource>>() {
            @Override
            public List<Resource> answerAs(Actor actor) {
                SearchApiAbility ability = actor.uses(SearchApiAbility.class);
                return ability.getLastResponse();
            }
        };
    }

}