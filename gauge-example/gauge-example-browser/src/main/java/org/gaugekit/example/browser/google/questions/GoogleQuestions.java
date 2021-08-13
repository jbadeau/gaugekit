package org.gaugekit.example.browser.google.questions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.gaugekit.example.browser.common.abilities.SurfTheWeb;
import org.gaugekit.example.browser.google.pages.GoogleSearchPage;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Question;

public class GoogleQuestions {

    private GoogleQuestions() {
    }

    public static Question<SelenideElement> searchResult(int index) {
        return new Question<SelenideElement>() {
            @Override
            public SelenideElement answerAs(Actor actor) {
                SurfTheWeb ability = actor.uses(SurfTheWeb.class);
                Selenide browser = ability.getBrowser();
                GoogleSearchPage page = browser.page(GoogleSearchPage.class);
                return page.searchResults.getSearchResult(index);
            }
        };
    }

}
