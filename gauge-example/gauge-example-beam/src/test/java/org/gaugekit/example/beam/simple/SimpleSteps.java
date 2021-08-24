package org.gaugekit.example.beam.simple;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.commons.io.FilenameUtils;
import org.gaugekit.common.io.TableReader;
import org.gaugekit.example.beam.common.question.TemplateQuestions;
import org.gaugekit.example.beam.common.task.RenderTasks;
import org.gaugekit.example.beam.simple.task.SimpleTasks;
import org.gaugekit.screenplay.Actor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;

import static org.gaugekit.table.matcher.TableHasTheSameRowsAs.hasTheSameRowsAs;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleSteps implements SimpleMemories {

    @Step("When <actor> provides templated input <template>")
    public void source(Actor actor, String template) {
        actor.attemptsTo(RenderTasks.renderTemplateToFile(template, Collections.EMPTY_MAP));
        actor.remembers(INPUT, TemplateQuestions.getLastRenderedFile());
    }

    @Step("And <he> runs <pipeline> pipeline")
    public void run(Actor actor, String pipeline) {
        Path source = actor.recites(INPUT);
        Path output = source.getParent().resolve(FilenameUtils.removeExtension(source.getFileName().toString()) + ".output.csv");
        actor.remembers(OUTPUT, output);

        PipelineOptionsFactory.register(SimplePipelineOptions.class);
        String[] args = new String[2];
        args[0] = "--input=" + source.toAbsolutePath().toString();
        args[1] = "--output=" + output.toAbsolutePath().toString();
        SimplePipelineOptions options = PipelineOptionsFactory.fromArgs(args)
                .withValidation()
                .as(SimplePipelineOptions.class);
        actor.attemptsTo(SimpleTasks.runSimplePipeline(options));
    }

    @Step("Then <actor> ensures output matches snapshot <snapshot>")
    public void verify(Actor actor, Path      snapshot) throws IOException {
        Path output = actor.recites(OUTPUT);
        Table sourceTable = TableReader.fromCsv(snapshot);
        Table outputTable = TableReader.fromCsv(output);
        assertThat(sourceTable, hasTheSameRowsAs(outputTable));
    }

}