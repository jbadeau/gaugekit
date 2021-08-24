package org.gaugekit.example.api.rest.google;

import com.thoughtworks.gauge.*;
import org.gaugekit.common.io.FileReader;
import org.gaugekit.examples.ui.google.search.api.SearchApi;
import org.gaugekit.examples.ui.google.search.client.ApiClient;
import org.gaugekit.examples.ui.google.search.model.Resource;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import org.mockserver.client.MockServerClient;
import org.testcontainers.containers.MockServerContainer;

import java.util.List;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import static org.gaugekit.examples.ui.google.search.client.JacksonObjectMapper.jackson;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static io.restassured.config.RestAssuredConfig.config;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GoogleSearchSteps {

    private SearchApi api;

    public MockServerContainer mockServer;

    @BeforeScenario
    public void beforeScenario() {
        mockServer = new MockServerContainer();
        mockServer.start();

        MockServerClient client = new MockServerClient(mockServer.getHost(), mockServer.getServerPort());

        client
                .when(request().withPath("/resources")
                        .withQueryStringParameter("query", "Gauge"))
                .respond(response()
                        .withStatusCode(200)
                        .withBody(FileReader.contentOf("search-resources.json")));

        api = ApiClient.api(ApiClient.Config.apiConfig().reqSpecSupplier(
                () -> new RequestSpecBuilder()
                        .setConfig(config().objectMapperConfig(objectMapperConfig().defaultObjectMapper(jackson())))
                        .addFilter(new ErrorLoggingFilter())
                        .setBaseUri(String.format("http://%s:%s", mockServer.getHost(), mockServer.getServerPort())))).search();
    }

    @AfterScenario
    public void afterScenario() {
        mockServer.stop();
    }

    @Step("goto google")
    public void gotoGoogle() {
    }

    @Step("search for <query>")
    public void searchForQuery(String query) {
        List<Resource> resources = api.search()
                .queryQuery(query)
                .execute(r -> r.prettyPeek())
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .jsonPath().getList(".", Resource.class);
        DataStoreFactory.getScenarioDataStore().put("resources", resources);
    }

    @Step("<title> is the first result item")
    public void titleIsTheFirstResultItem(String title) {
        List<Resource> resources = (List<Resource>) DataStoreFactory.getScenarioDataStore().get("resources");
        assertThat(resources.get(0).getTitle(), equalTo(title));
    }

}