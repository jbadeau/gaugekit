package org.gaugekit.sauron.assertion;

import org.assertj.core.api.AbstractAssert;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class IsEmpty extends AbstractAssert<IsEmpty, ImageDiff> {

    public IsEmpty(ImageDiff diff) {
        super(diff, IsEmpty.class);
    }


    public static IsEmpty assertThat(ImageDiff diff) {
        return new IsEmpty(diff);
    }

    public IsEmpty isEmpty(ImageDiff diff) {
        isNotNull();
        if(diff.hasDiff()) {
            failWithMessage("Expected image diff to be empty but there were diffs");
        }
        return this;
    }

}
