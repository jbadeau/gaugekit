package org.gaugekit.template.screenplay;

import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Question;

import java.nio.file.Path;

public interface TemplateQuestions {

    default Question<Path> lastRenderedTemplate() {
        return new Question<Path>() {
            @Override
            public Path answerAs(Actor actor) {
                TemplateAbility ability = actor.uses(TemplateAbility.class);
                return ability.lastRenderedTemplate();
            }
        };
    }

}
