package org.gaugekit.example.beam.common.ability;

import org.gaugekit.screenplay.Ability;
import org.gaugekit.template.TemplateRenderer;

import java.nio.file.Path;

public class RenderTemplatesAbility implements Ability {

    private TemplateRenderer renderer;

    private Path lastRenderedFile;

    public RenderTemplatesAbility() {
        renderer = new TemplateRenderer();
    }

    public static Ability renderTemplates() {
        return new RenderTemplatesAbility();
    }

    public TemplateRenderer getRenderer() {
        return this.renderer;
    }

    public Path getLastRenderedFile() {
        return lastRenderedFile;
    }

    public void setLastRenderedFile(Path file) {
        this.lastRenderedFile = file;
    }

}
