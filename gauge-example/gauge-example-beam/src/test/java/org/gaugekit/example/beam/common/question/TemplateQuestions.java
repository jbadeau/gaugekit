package org.gaugekit.example.beam.common.question;

import org.gaugekit.example.beam.common.ability.RenderTemplatesAbility;
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
                RenderTemplatesAbility ability = actor.uses(RenderTemplatesAbility.class);
                return ability.getLastRenderedFile();
            }
        };
    }

}
