package org.gaugekit.template.screenplay;

import org.gaugekit.core.screenplay.Ability;
import org.gaugekit.template.TemplateRenderer;

import java.nio.file.Path;

public class TemplateAbility implements Ability {

    private TemplateRenderer renderer;

    private Path lastRenderedFile;

    public TemplateAbility() {
        renderer = new TemplateRenderer();
    }

    public static Ability template() {
        return new TemplateAbility();
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
