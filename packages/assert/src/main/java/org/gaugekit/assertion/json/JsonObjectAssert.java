package org.gaugekit.assertion.json;

import org.assertj.core.api.AbstractAssert;
import org.json.JSONObject;

public class JsonObjectAssert extends AbstractAssert<JsonObjectAssert, JSONObject> {

    public JsonObjectAssert(JSONObject actual) {
        super(actual, JsonObjectAssert.class);
    }

    public static JsonObjectAssert assertThat(JSONObject actual) {
        return new JsonObjectAssert(actual);
    }

}
