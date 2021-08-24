package org.gaugekit.example.api.graphql.google;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import org.gaugekit.common.io.FileReader;
import org.gaugekit.examples.api.graphql.google.QueryExecutor;
import org.gaugekit.examples.api.graphql.google.Resource;
import org.mockserver.client.MockServerClient;
import org.testcontainers.containers.MockServerContainer;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


public class GoogleSearchSteps {

    private QueryExecutor query;

    public MockServerContainer mockServer;

    @BeforeScenario
    public void beforeScenario() {
        mockServer = new MockServerContainer();
        mockServer.start();

        MockServerClient client = new MockServerClient(mockServer.getHost(), mockServer.getServerPort());

        client
                .when(request()
                        .withPath("/")
                        .withMethod("POST"))
                .respond(response()
                        .withStatusCode(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(FileReader.contentOf("search-resources.json")));

        query = new QueryExecutor(String.format("http://%s:%s", mockServer.getHost(), mockServer.getServerPort()));
    }

    @AfterScenario
    public void afterScenario() {
        mockServer.stop();
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