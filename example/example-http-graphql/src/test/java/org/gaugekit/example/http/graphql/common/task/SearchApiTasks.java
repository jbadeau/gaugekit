package org.gaugekit.example.http.graphql.common.task;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;
import org.gaugekit.example.http.graphql.common.ability.SearchApiAbility;
import org.gaugekit.example.http.graphql.google.QueryExecutor;
import org.gaugekit.example.http.graphql.google.Resource;
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
                try {
                    SearchApiAbility ability = actor.uses(SearchApiAbility.class);
                    QueryExecutor api = ability.getApi();
                    List<Resource> resources = api.search("", query);
                    ability.setLastResponse(resources);
                } catch (GraphQLRequestExecutionException e) {
                    throw new RuntimeException(e);
                } catch (GraphQLRequestPreparationException e) {
                    throw new RuntimeException(e);
                }
            }

        };
    }

}