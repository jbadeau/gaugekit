package org.gaugekit.template.screenplay;

import org.gaugekit.core.screenplay.Ability;
import org.gaugekit.template.TemplateRenderer;

import java.nio.file.Path;

public class RenderTemplates implements Ability {

    private TemplateRenderer renderer;

    private Path lastRenderedFile;

    public TemplateRenderer getRenderer() {
        return this.renderer;
    }

    public Path lastRenderedTemplate() {
        return lastRenderedFile;
    }

    public void setLastRenderedFile(Path file) {
        this.lastRenderedFile = file;
    }

}
