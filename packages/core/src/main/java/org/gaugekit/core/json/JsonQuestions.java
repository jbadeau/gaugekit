package org.gaugekit.core.json;

import com.google.gson.JsonElement;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Question;

public interface JsonQuestions {

    default Question<JsonElement> jsonAt(String file) {
        return new Question<JsonElement>() {
            @Override
            public JsonElement answerAs(Actor actor) {
                JsonAbility ability = actor.uses(JsonAbility.class);
                return JsonReader.read(file);
            }
        };
    }


}