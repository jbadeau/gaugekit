package org.gaugekit.example.api.graphql.google;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;
import com.thoughtworks.gauge.BeforeSpec;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.DataStoreFactory;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class GoogleSearchSteps {

    private Query query;

    @BeforeSpec
    public void beforeSpec() {
        query = new Query(GoogleProperties.getGoogleBaseUrl());
    }

    @Step("goto google")
    public void gotoGoogle() {
    }

    @Step("search for <query>")
    public void searchForQuery(String query) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
        List<Resource> resources = this.query.search("", query);
        DataStoreFactory.getScenarioDataStore().put("resources", resources);
    }

    @Step("<title> is the first result item")
    public void titleIsTheFirstResultItem(String title) {
        List<Resource> resources = (List<Resource>) DataStoreFactory.getScenarioDataStore().get("resources");
        assertThat(resources.get(0).getTitle(), equalTo(title));
    }

}