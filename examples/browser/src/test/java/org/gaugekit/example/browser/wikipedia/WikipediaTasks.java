package org.gaugekit.example.browser.wikipedia;

import org.gaugekit.browser.screenplay.BrowseTheWeb;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Task;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WikipediaTasks {

    private WikipediaTasks() {
    }

    public static Task searchFor(String query) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                BrowseTheWeb ability = actor.uses(BrowseTheWeb.class);
                WebDriver driver = ability.getWebDriver();
                WebElement element = driver.findElement(By.name("search"));
                element.sendKeys(query);
                element.sendKeys(Keys.ENTER);
            }
        };
    }

}
