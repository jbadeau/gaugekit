package org.gaugekit.example.beam.common.task;

import org.gaugekit.example.beam.common.ability.RenderTemplatesAbility;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Task;

import java.nio.file.Path;
import java.util.Map;

public class RenderTasks {

    private RenderTasks() {
    }

    public static Task renderTemplateToFile(String template, Map<String, Object> values) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                RenderTemplatesAbility ability = actor.uses(RenderTemplatesAbility.class);
                Path file = ability.getRenderer().renderToFile(template, values);
                ability.setLastRenderedFile(file);
            }
        };
    }

}
