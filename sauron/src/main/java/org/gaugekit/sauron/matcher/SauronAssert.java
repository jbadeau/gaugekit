package org.gaugekit.sauron.matcher;

import com.thoughtworks.gauge.Table;
import org.assertj.core.api.AbstractAssert;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SauronAssert extends AbstractAssert<SauronAssert, ImageDiff> {

    public SauronAssert(ImageDiff diff) {
        super(diff, SauronAssert.class);
    }


    public static SauronAssert assertThat(ImageDiff diff) {
        return new SauronAssert(diff);
    }

    public SauronAssert isEmpty(ImageDiff diff) {
        isNotNull();
        if(diff.hasDiff()) {
            failWithMessage("Expected image diff to be empty but there were diffs");
        }
        return this;
    }

}
