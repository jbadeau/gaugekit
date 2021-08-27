package org.gaugekit.template.screenplay;

import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Question;

import java.nio.file.Path;

public interface TemplateQuestions {

    default Question<Path> getLastRenderedTemplate() {
        return new Question<Path>() {
            @Override
            public Path answerAs(Actor actor) {
                TemplateAbility ability = actor.uses(TemplateAbility.class);
                return ability.getLastRenderedFile();
            }
        };
    }

}
