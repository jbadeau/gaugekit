package org.gaugekit.template.screenplay;

import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Question;

import java.nio.file.Path;

public class TemplateQuestions {

    public static Question<Path> lastRenderedTemplate() {
        return new Question<Path>() {
            @Override
            public Path answerAs(Actor actor) {
                RenderTemplates ability = actor.uses(RenderTemplates.class);
                return ability.lastRenderedTemplate();
            }
        };
    }

}
