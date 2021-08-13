package org.gaugekit.example.beam.common.abilities;

import org.gaugekit.screenplay.Ability;
import org.gaugekit.template.TemplateRenderer;

import java.io.File;

public class RenderTemplates implements Ability {

    private TemplateRenderer renderer;

    private File lastRenderedFile;

    public RenderTemplates() {
        renderer = new TemplateRenderer();
    }

    public static Ability renderTemplates() {
        return new RenderTemplates();
    }

    public TemplateRenderer getRenderer() {
        return this.renderer;
    }

    public File getLastRenderedFile() {
        return lastRenderedFile;
    }

    public void setLastRenderedFile(File file) {
        this.lastRenderedFile = file;
    }

}
