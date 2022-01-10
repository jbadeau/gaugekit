package org.gaugekit.example.browser.wikipedia.screenplay.interaction;

import org.gaugekit.browser.screenplay.BrowseTheWeb;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Performable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EnterText implements Performable {

    public EnterText() {
    }

    private String value;

    private WebElement target;

    private By by;

    public EnterText(String value) {
        this.value = value;
    }

    public static EnterText value(String value) {
        return new EnterText(value);
    }

    public Performable into(By by) {
        this.by = by;
        return this;
    }

    public Performable into(WebElement target) {
        this.target = target;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.uses(BrowseTheWeb.class).getWebDriver().findElement(by).sendKeys(value);
    }
}