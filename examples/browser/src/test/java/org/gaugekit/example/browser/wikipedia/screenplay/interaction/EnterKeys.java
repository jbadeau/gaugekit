package org.gaugekit.example.browser.wikipedia.screenplay.interaction;

import org.gaugekit.browser.screenplay.BrowseTheWeb;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Performable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class EnterKeys implements Performable {

    public EnterKeys() {
    }

    private Keys value;

    private WebElement target;

    private By by;

    public EnterKeys(Keys value) {
        this.value = value;
    }

    public static EnterKeys value(Keys value) {
        return new EnterKeys(value);
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