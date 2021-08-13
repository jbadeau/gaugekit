package org.gaugekit.example.beam.simple.questions;

import org.gaugekit.example.beam.common.abilities.RenderTemplates;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Question;

import java.io.File;

public class TemplateQuestions {

    private TemplateQuestions() {
    }

    public static Question<File> getLastRenderedFile() {
        return new Question<File>() {
            @Override
            public File answerAs(Actor actor) {
                RenderTemplates ability = actor.uses(RenderTemplates.class);
                return ability.getLastRenderedFile();
            }
        };
    }

}
