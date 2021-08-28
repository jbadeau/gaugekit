package org.gaugekit.example.beam.simple;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;

public class SimplePipeline {

    private SimplePipeline() {
    }

    public static void runSimplePipeline(SimplePipelineOptions options) {
        Pipeline p = Pipeline.create(options);

        p
                .apply("Read input CSV file", TextIO.read().from(options.getInput()))
                .apply("Write output CSV file", TextIO.write().withoutSharding().to(options.getOutput()));

        p.run().waitUntilFinish();
    }

}
