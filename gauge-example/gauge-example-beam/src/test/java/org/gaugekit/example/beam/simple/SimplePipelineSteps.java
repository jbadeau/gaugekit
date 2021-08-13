package org.gaugekit.example.beam.simple;

import org.gaugekit.example.beam.common.abilities.RenderTemplates;
import org.gaugekit.example.beam.common.abilities.RunBeamPipelines;
import org.gaugekit.example.beam.simple.questions.TemplateQuestions;
import org.gaugekit.example.beam.simple.tasks.RenderTasks;
import org.gaugekit.example.beam.simple.tasks.SimpleTasks;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Cast;
import org.gaugekit.common.io.TableReader;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import static org.gaugekit.table.matcher.TableHasTheSameRowsAs.hasTheSameRowsAs;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimplePipelineSteps implements SimpleMemories {

    @Step("Given actor <Mark>")
    public void actor(Actor actor) {
        Cast.addActor(actor);
        actor
                .can(RunBeamPipelines.runBeamPipelines())
                .can(RenderTemplates.renderTemplates());
    }

    @Step("And templated input <template>")
    public void source(String template) {
        Actor actor = Cast.getActorInSpotlight();
        actor.attemptTo(RenderTasks.renderTemplateToFile(template, Collections.EMPTY_MAP));
        actor.remember(INPUT, TemplateQuestions.getLastRenderedFile());
    }

    @Step("When run <pipeline> pipeline")
    public void run(String pipeline) {
        Actor actor = Cast.getActorInSpotlight();
        File source = actor.recall(INPUT);
        File output = new File(source.getParent(), FilenameUtils.removeExtension(source.getName()) + ".output.csv");
        actor.remember(OUTPUT, output);

        PipelineOptionsFactory.register(SimplePipelineOptions.class);
        String[] args = new String[2];
        args[0] = "--input=" + source.getAbsolutePath();
        args[1] = "--output=" + output.getAbsolutePath();
        SimplePipelineOptions options = PipelineOptionsFactory.fromArgs(args)
                .withValidation()
                .as(SimplePipelineOptions.class);
        actor.attemptTo(SimpleTasks.runSimplePipeline(options));
    }

    @Step("Then output matches snapshot <snapshot>")
    public void verify(File snapshot) throws IOException {
        Actor actor = Cast.getActorInSpotlight();
        File output = actor.recall(OUTPUT);
        Table sourceTable = TableReader.readCsvTable(snapshot);
        Table outputTable = TableReader.readCsvTable(output);
        assertThat(sourceTable, hasTheSameRowsAs(outputTable));
    }

}
