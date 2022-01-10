package org.gaugekit.example.http.rest.common.task;

import org.gaugekit.example.http.rest.common.ability.SearchApiAbility;
import org.gaugekit.example.http.rest.google.search.api.SearchApi;
import org.gaugekit.example.http.rest.google.search.model.Resource;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Task;

import java.util.List;

public class SearchApiTasks {

    private SearchApiTasks() {
    }

    public static Task search(String query) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                SearchApiAbility ability = actor.uses(SearchApiAbility.class);
                SearchApi api = ability.getApi();
                List<Resource> resources = api.search()
                        .queryQuery(query)
                        .execute(r -> r.prettyPeek())
                        .then()
                        .statusCode(200)
                        .extract()
                        .body().jsonPath().getList(".", Resource.class);
                ability.setLastResponse(resources);
            }
        };
    }

}