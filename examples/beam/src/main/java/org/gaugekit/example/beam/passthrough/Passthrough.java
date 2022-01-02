package org.gaugekit.example.beam.passthrough;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;

public class Passthrough {

    public static void main(String[] args) {
        PassthroughOptions options = PipelineOptionsFactory.fromArgs(args)
                .withValidation()
                .as(PassthroughOptions.class);
        instanceOf(options).run();
    }

    public static Pipeline instanceOf(PassthroughOptions options) {
        Pipeline p = Pipeline.create(options);

        p
                .apply("Read input CSV file", TextIO.read().from(options.getSource()))
                .apply("Write output CSV file", TextIO.write().withoutSharding().to(options.getSink()));

        return p;
    }

    public static void run(PassthroughOptions options) {

    }

}
