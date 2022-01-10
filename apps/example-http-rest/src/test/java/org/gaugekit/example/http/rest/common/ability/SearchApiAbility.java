package org.gaugekit.example.http.rest.common.ability;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import org.gaugekit.example.http.rest.google.search.api.SearchApi;
import org.gaugekit.example.http.rest.google.search.client.ApiClient;
import org.gaugekit.example.http.rest.google.search.model.Resource;
import org.gaugekit.core.screenplay.Ability;

import java.util.List;

import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static io.restassured.config.RestAssuredConfig.config;
import static org.gaugekit.example.http.rest.google.search.client.JacksonObjectMapper.jackson;

public class SearchApiAbility implements Ability {

    private SearchApi api;

    private List<Resource> lastResponse;

    public SearchApiAbility(String url) {
        api = ApiClient.api(ApiClient.Config.apiConfig().reqSpecSupplier(
                () -> new RequestSpecBuilder()
                        .setConfig(config().objectMapperConfig(objectMapperConfig().defaultObjectMapper(jackson())))
                        .addFilter(new ErrorLoggingFilter())
                        .setBaseUri(url))).search();
    }

    public static Ability searchApi(String url) {
        return new SearchApiAbility(url);
    }

    public SearchApi getApi() {
        return api;
    }

    public List<Resource> getLastResponse() {
        return lastResponse;
    }

    public void setLastResponse(List<Resource> lastResponse) {
        this.lastResponse = lastResponse;
    }

}