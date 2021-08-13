package org.gaugekit.template;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.gaugekit.common.property.CommonProperties;
import org.gaugekit.common.io.FileReader;
import org.gaugekit.template.helper.DateTimeHelpers;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TemplateRenderer {

    private Handlebars handlebars;

    public TemplateRenderer() {
        handlebars = new Handlebars();
        registerHelpers(DateTimeHelpers.class);
    }

    public String render(String template, Map<String, Object> values) {
        try {
            Template tmp = compileInline(template);
            return tmp.apply(values);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to render template '%s' with values '%s'", template, values), e);
        }
    }

    public File renderToFile(String template, Map<String, Object> values) {
        try {
            File templateFile = FileReader.fileAt(template);
            String templateContent = render(FileReader.contentOf(templateFile), values);
            String fileName = compileInline(templateFile.getName()).apply(values);
            File file = new File(new File(CommonProperties.getTmpDir(), FileReader.parseEnv(templateFile)), FilenameUtils.removeExtension(fileName));
            FileUtils.writeStringToFile(file, templateContent, StandardCharsets.UTF_8);
            return file;
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to render template '%s' with values '%s'", template, values), e);
        }
    }

    public Template compile(File template) {
        try {
            return handlebars.compile(FileReader.contentOf(template));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to compile file template '%s'", template), e);
        }
    }

    public Template compileInline(String template) {
        try {
            return handlebars.compileInline(template);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to compile template '%s'", template), e);
        }
    }

    public void registerHelpers(Class helpers) {
        handlebars.registerHelpers(helpers);
    }

}
