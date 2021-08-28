package org.gaugekit.example.beam.simple;

import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;

public interface SimplePipelineOptions extends PipelineOptions {

    @Description("Input for the pipeline")
    String getInput();

    void setInput(String input);

    @Description("Input for the pipeline")
    String getOutput();

    void setOutput(String output);

}