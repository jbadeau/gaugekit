package org.gaugekit.example.browser.google.tasks;

import com.codeborne.selenide.Selenide;
import org.gaugekit.example.browser.common.abilities.SurfTheWeb;
import org.gaugekit.example.browser.google.pages.GoogleSearchPage;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Task;

public class GoogleTasks {

    private GoogleTasks() {
    }

    public static Task search(String query) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                SurfTheWeb ability = actor.uses(SurfTheWeb.class);
                Selenide browser = ability.getBrowser();
                GoogleSearchPage page = browser.page(GoogleSearchPage.class);
                page.search.search(query);
            }
        };
    }

}
