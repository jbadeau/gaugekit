package org.gaugekit.template.screenplay;

import org.gaugekit.screenplay.Ability;
import org.gaugekit.template.TemplateRenderer;

import java.nio.file.Path;

public class RenderTemplateAbility implements Ability {

    private TemplateRenderer renderer;

    private Path lastRenderedFile;

    public RenderTemplateAbility() {
        renderer = new TemplateRenderer();
    }

    public static Ability renderTemplates() {
        return new RenderTemplateAbility();
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
