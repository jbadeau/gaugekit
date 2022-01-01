package org.gaugekit.example.beam.wordcount;

import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;

public interface WordCountOptions extends PipelineOptions {

    @Description("Source for the pipeline")
    String getSource();

    void setSource(String source);

    @Description("Sink for the pipeline")
    String getSink();

    void setSink(String sink);

}