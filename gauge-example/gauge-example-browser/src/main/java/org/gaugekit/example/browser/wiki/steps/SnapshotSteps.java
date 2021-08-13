package org.gaugekit.example.browser.wiki.steps;

import org.gaugekit.sauron.webdriver.Sauron;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import org.openqa.selenium.By;

import java.util.Set;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.gaugekit.sauron.matcher.MatchesBaseline.matchesBaseline;
import static org.hamcrest.MatcherAssert.assertThat;

public class SnapshotSteps {

    protected Sauron getSauron() {
        return (Sauron) DataStoreFactory.getSuiteDataStore().get("sauron");
    }

    protected void openEye() {
        getSauron().openEye(getWebDriver());
    }

    protected void closeEye() {
        getSauron().closeEye();
    }

    protected void checkWindow(String tag) {
        openEye();
        sleep();
        assertThat(getSauron().gaze(tag), matchesBaseline());
        closeEye();
    }

    protected void checkWindow(String tag, Set<By> ignoreSelectors) {
        openEye();
        sleep();
        assertThat(getSauron().gaze(tag, ignoreSelectors), matchesBaseline());
        closeEye();
    }

    protected void checkWindow(String tag, By ignoreSelector) {
        openEye();
        sleep();
        assertThat(getSauron().gaze(tag, ignoreSelector), matchesBaseline());
        closeEye();
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
