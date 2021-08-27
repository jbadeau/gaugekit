package org.gaugekit.example.http.rest;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import org.gaugekit.core.util.DataUtils;
import org.gaugekit.example.http.rest.common.ability.SearchApiAbility;
import org.gaugekit.example.http.rest.common.memory.CommonMemories;
import org.gaugekit.screenplay.Cast;
import org.gaugekit.screenplay.Director;
import org.mockserver.client.MockServerClient;
import org.testcontainers.containers.MockServerContainer;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class Hooks implements CommonMemories {

    @BeforeScenario
    public void beforeScenario() {
        MockServerContainer mockServer = new MockServerContainer();
        mockServer.start();
        ScenarioDataStore.put(MOCK_SERVER, mockServer);
        MockServerClient client = new MockServerClient(mockServer.getHost(), mockServer.getServerPort());

        client
                .when(request().withPath("/resources")
                        .withQueryStringParameter("query", "Shakespeare"))
                .respond(response()
                        .withStatusCode(200)
                        .withBody(DataUtils.contentOf("resources.json")));

        Cast cast = new Cast();
        cast.actorNamed("John", SearchApiAbility.searchApi(String.format("http://%s:%s", mockServer.getHost(), mockServer.getServerPort())));
        Director.setStage(cast);
    }

    @AfterScenario
    public void afterScenario() {
        MockServerContainer mockServer = (MockServerContainer) ScenarioDataStore.get(MOCK_SERVER);
        mockServer.stop();
    }

}
