package org.gaugekit.template.screenplay;


import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Task;

import java.nio.file.Path;
import java.util.Map;

public class TemplateTasks {

    public static Task renderTemplate(String template, Map<String, Object> values) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                RenderTemplates ability = actor.uses(RenderTemplates.class);
                Path file = ability.getRenderer().renderToFile(template, values);
                ability.setLastRenderedFile(file);
            }
        };
    }

}

