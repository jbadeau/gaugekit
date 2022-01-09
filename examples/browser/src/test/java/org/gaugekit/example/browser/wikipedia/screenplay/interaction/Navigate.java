package org.gaugekit.example.browser.wikipedia.screenplay.interaction;

import org.gaugekit.browser.screenplay.BrowseTheWeb;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Performable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Navigate implements Performable {

    public Navigate() {
    }

    private String url;

    private WebElement target;

    private By by;

    public Navigate(String url) {
        this.url = url;
    }

    public static Navigate to(String url) {
        return new Navigate(url);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.uses(BrowseTheWeb.class).getWebDriver().navigate().to(url);
    }

}