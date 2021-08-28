package org.gaugekit.example.http.graphql.common.question;

import org.gaugekit.example.http.graphql.common.ability.SearchApiAbility;
import org.gaugekit.example.http.graphql.google.Resource;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Question;

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