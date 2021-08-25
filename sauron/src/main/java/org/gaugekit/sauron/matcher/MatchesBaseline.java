package org.gaugekit.sauron.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.util.Collection;

public class MatchesBaseline extends TypeSafeDiagnosingMatcher<Collection<ImageDiff>> {

    @Override
    protected boolean matchesSafely(Collection<ImageDiff> imageDiffs, Description description) {
        for(ImageDiff diff : imageDiffs) {
            if(diff.hasDiff()) {
                description.appendText("failed");
                return  false;
            }
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("passed");
    }

    public static MatchesBaseline matchesBaseline() {
        return new MatchesBaseline();
    }
}
