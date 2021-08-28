package org.gaugekit.example.http.graphql.common.ability;

import org.gaugekit.example.http.graphql.google.QueryExecutor;
import org.gaugekit.example.http.graphql.google.Resource;
import org.gaugekit.core.screenplay.Ability;

import java.util.List;

public class SearchApiAbility implements Ability {

    private QueryExecutor api;

    private List<Resource> lastResponse;

    public SearchApiAbility(String url) {
        api = new QueryExecutor(url);
    }

    public static Ability searchApi(String url) {
        return new SearchApiAbility(url);
    }

    public QueryExecutor getApi() {
        return api;
    }

    public List<Resource> getLastResponse() {
        return lastResponse;
    }

    public void setLastResponse(List<Resource> lastResponse) {
        this.lastResponse = lastResponse;
    }

}