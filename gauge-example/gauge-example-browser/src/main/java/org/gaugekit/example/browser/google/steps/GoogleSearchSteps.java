package org.gaugekit.example.browser.google.steps;

import com.codeborne.selenide.SelenideElement;
import org.gaugekit.example.browser.common.tasks.BrowserTasks;
import org.gaugekit.example.browser.google.GoogleProperties;
import org.gaugekit.example.browser.google.questions.GoogleQuestions;
import org.gaugekit.example.browser.google.tasks.GoogleTasks;
import org.gaugekit.screenplay.Cast;
import com.thoughtworks.gauge.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleSearchSteps {
    
    @Step("goto google")
    public void openGoogle() {
        Cast.getActorInSpotlight().attemptTo(BrowserTasks.open(GoogleProperties.getGoogleBaseUrl()));
    }

    @Step("search for <query>")
    public void search(String query) {
        Cast.getActorInSpotlight().attemptTo(GoogleTasks.search(query));
    }

    @Step("<title> is the first result item")
    public void results(String title) {
        SelenideElement result = Cast.getActorInSpotlight().askFor(GoogleQuestions.searchResult(0));
        assertThat(result.text()).contains(title);
    }

}