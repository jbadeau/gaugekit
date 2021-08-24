package org.gaugekit.example.beam.common.question;

import org.gaugekit.example.beam.common.ability.RenderTemplates;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Question;

import java.nio.file.Path;

public class TemplateQuestions {

    private TemplateQuestions() {
    }

    public static Question<Path> getLastRenderedFile() {
        return new Question<Path>() {
            @Override
            public Path answerAs(Actor actor) {
                RenderTemplates ability = actor.uses(RenderTemplates.class);
                return ability.getLastRenderedFile();
            }
        };
    }

}
