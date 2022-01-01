package org.gaugekit.example.beam.wordcount;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.TypeDescriptors;

import java.util.Arrays;

public class WordCount {

    public static void main(String[] args) {
        WordCountOptions options = PipelineOptionsFactory.fromArgs(args)
                .withValidation()
                .as(WordCountOptions.class);
        instanceOf(options).run();
    }

    public static Pipeline instanceOf(WordCountOptions options) {
        Pipeline p = Pipeline.create(options);

        p
                .apply("Read input CSV file", TextIO.read().from(options.getSource()))
                .apply("Write output CSV file", TextIO.write().withoutSharding().to(options.getSink()));

        return p;
    }

    public static void run(WordCountOptions options) {

    }

}
