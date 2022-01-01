package org.gaugekit.example.beam.wordcount;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.gaugekit.core.file.FileQuestions;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.table.TableQuestions;
import org.gaugekit.template.screenplay.TemplateQuestions;
import org.gaugekit.template.screenplay.TemplateTasks;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

public class WordCountSteps implements TemplateTasks, TemplateQuestions, TableQuestions, WordCountMemories {

    @Step("Given <actor> provides source <sourceFile> and sink <sinkFile>")
    public void source(Actor actor, Path sourceFile, Path sinkFile) {
        actor.memorizes(SOURCE, sourceFile);
        actor.memorizes(SINK, sinkFile);
    }

    @Step("When <he> runs pipeline <pipeline>")
    public void run(Actor actor, String pipeline) {
        Path source = actor.recites(SOURCE);
        Path sink = actor.recites(SINK);

        PipelineOptionsFactory.register(WordCountOptions.class);
        WordCountOptions options = PipelineOptionsFactory.create().as(WordCountOptions.class);
        options.setSource(source.toString());
        options.setSink(sink.toString());

        actor.attemptsTo(RunBeamPipeline.withOptions(options));
    }

    @Step("Then <actor> ensures sink <outputFile> isEqualTo snapshot <snapshotFile>")
    public void verify(Actor actor, Path outputFile, Path snapshotFile) {
        String output = actor.asksFor(FileQuestions.contentsOf(outputFile));
        String snapshot = actor.asksFor(FileQuestions.contentsOf(snapshotFile));
        assertThat(output.trim()).isEqualTo(snapshot.trim());
    }

}
