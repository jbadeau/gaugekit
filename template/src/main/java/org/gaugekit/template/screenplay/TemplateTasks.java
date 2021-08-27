package org.gaugekit.template.screenplay;


import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Task;

import java.nio.file.Path;
import java.util.Map;

public interface TemplateTasks {


    default Task renderTemplateToFile(String template, Map<String, Object> values) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                TemplateAbility ability = actor.uses(TemplateAbility.class);
                Path file = ability.getRenderer().renderToFile(template, values);
                ability.setLastRenderedFile(file);
            }
        };
    }

}

