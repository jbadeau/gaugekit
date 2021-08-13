package org.gaugekit.example.browser.wiki.steps;

import com.codeborne.selenide.Selenide;
import org.gaugekit.example.browser.wiki.WikiProperties;
import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class WikiSnapshotSteps extends SnapshotSteps {

    @Step("Search for Gauge")
    public void searchForGauge() {
        Selenide.open(WikiProperties.getWikiBaseUrl());
        checkWindow("home-page", new By.ById("articlecount"));
        $("#searchInput").sendKeys("Gauge");
        $("#searchform").$("* [type='submit']").click();
        checkWindow("Gauge");
    }

}