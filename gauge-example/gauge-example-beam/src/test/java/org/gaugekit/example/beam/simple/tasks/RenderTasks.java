package org.gaugekit.example.beam.simple.tasks;

import org.gaugekit.example.beam.common.abilities.RenderTemplates;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Task;

import java.io.File;
import java.util.Map;

public class RenderTasks {

    private RenderTasks() {
    }

    public static Task renderTemplateToFile(String template, Map<String, Object> values) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                RenderTemplates ability = actor.uses(RenderTemplates.class);
                File file = ability.getRenderer().renderToFile(template, values);
                ability.setLastRenderedFile(file);
            }
        };
    }

}
