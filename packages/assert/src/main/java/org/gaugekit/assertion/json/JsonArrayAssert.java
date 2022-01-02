package org.gaugekit.assertion.json;

import org.assertj.core.api.AbstractAssert;
import org.json.JSONArray;

public class JsonArrayAssert extends AbstractAssert<JsonArrayAssert, JSONArray> {

    public JsonArrayAssert(JSONArray actual) {
        super(actual, JsonArrayAssert.class);
    }

    public static JsonArrayAssert assertThat(JSONArray actual) {
        return new JsonArrayAssert(actual);
    }

}
