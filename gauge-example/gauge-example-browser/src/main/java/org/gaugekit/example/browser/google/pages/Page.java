package org.gaugekit.example.browser.google.pages;

import com.codeborne.selenide.ElementsContainer;
import org.gaugekit.sauron.webdriver.Sauron;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import org.openqa.selenium.By;

import java.util.HashSet;
import java.util.Set;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.gaugekit.sauron.matcher.MatchesBaseline.matchesBaseline;
import static org.hamcrest.MatcherAssert.assertThat;

public class Page extends ElementsContainer {

    public void openEye() {
        getSauron().openEye(getWebDriver());
    }

    public void checkWindow(String tag) {
        openEye();
        assertThat(getSauron().gaze(tag, getIgnoredSelectors()), matchesBaseline());
        closeEye();
    }

    public void closeEye() {
        getSauron().closeEye();
    }

    public Sauron getSauron() {
        return (Sauron) DataStoreFactory.getSuiteDataStore().get("sauron");
    }

    public Set<By> getIgnoredSelectors() {
        return new HashSet<By>();
    }

}
