package org.gaugekit.example.beam.passthrough;

import com.thoughtworks.gauge.Step;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.gaugekit.core.io.file.FileQuestions;
import org.gaugekit.core.screenplay.Actor;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.gaugekit.example.beam.passthrough.BeamTasks.runBeamPipeline;

public class PassthroughSteps implements PassthroughMemories {

    @Step("Given <actor> provides source <sourceFile> and sink <sinkFile>")
    public void source(Actor actor, Path sourceFile, Path sinkFile) {
        actor.memorizes(SOURCE, sourceFile);
        actor.memorizes(SINK, sinkFile);
    }

    @Step("When <he> runs pipeline <pipeline>")
    public void run(Actor actor, String pipeline) {
        Path source = actor.recites(SOURCE);
        Path sink = actor.recites(SINK);

        PipelineOptionsFactory.register(PassthroughOptions.class);
        PassthroughOptions options = PipelineOptionsFactory.create().as(PassthroughOptions.class);
        options.setSource(source.toString());
        options.setSink(sink.toString());

        actor.attemptsTo(runBeamPipeline(Passthrough.instanceOf(options)));
    }

    @Step("Then <actor> ensures sink <outputFile> isEqualTo snapshot <snapshotFile>")
    public void verify(Actor actor, Path outputFile, Path snapshotFile) {
        String output = actor.asksFor(FileQuestions.contentsOf(outputFile));
        String snapshot = actor.asksFor(FileQuestions.contentsOf(snapshotFile));
        assertThat(output.trim()).isEqualTo(snapshot.trim());
    }

}
