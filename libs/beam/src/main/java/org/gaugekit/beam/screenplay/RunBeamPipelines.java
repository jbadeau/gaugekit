package org.gaugekit.beam.screenplay;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.gaugekit.core.screenplay.Ability;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link org.gaugekit.core.screenplay.Ability} to run Apache Beam pipelines.
 */
public class RunBeamPipelines implements Ability {

    private List<PipelineResult> pipelineResults = new ArrayList();

    public void runPipeline(Pipeline pipeline) {
       pipelineResults.add(pipeline.run());
    }

    public PipelineResult getLastResults() {
        return pipelineResults.get(pipelineResults.size() -1);
    }

    public PipelineResult getLastResult(int index) {
        return pipelineResults.get(index);
    }

}