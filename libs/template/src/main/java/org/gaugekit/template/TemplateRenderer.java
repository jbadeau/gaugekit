package org.gaugekit.template;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.gaugekit.core.DefaultProperties;
import org.gaugekit.core.io.file.FileReader;
import org.gaugekit.core.io.file.PathUtils;
import org.gaugekit.template.helper.DateTimeHelpers;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
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

    public Path renderToFile(String template, Map<String, Object> values) {
        try {
            Path templateFile = PathUtils.resolveProjectPath(template);
            String templateContent = render(FileReader.read(templateFile), values);
            String fileName = compileInline(templateFile.getFileName().toString()).apply(values);
            Path file = templateFile.getParent().resolve(FilenameUtils.removeExtension(fileName));
            FileUtils.writeStringToFile(file.toFile(), templateContent, StandardCharsets.UTF_8);
            return file;
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to render template '%s' with values '%s'", template, values), e);
        }
    }

    public Template compile(File template) {
        try {
            return handlebars.compile(FileReader.read(template.toPath()));
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

    private static String parseEnv(Path file) {
        String dataPath = DefaultProperties.dataDir().toAbsolutePath().toString();
        String env = file.toAbsolutePath().toString().substring(dataPath.length() + 1);
        return new File(env.substring(0, env.indexOf(File.separator))).getName();
    }

}
