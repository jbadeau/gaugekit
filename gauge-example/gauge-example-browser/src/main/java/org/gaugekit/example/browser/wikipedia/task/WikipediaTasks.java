package org.gaugekit.example.browser.wikipedia.task;

import org.gaugekit.example.browser.common.ability.SurfTheWeb;
import org.gaugekit.screenplay.Actor;
import org.gaugekit.screenplay.Task;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WikipediaTasks {

    private WikipediaTasks() {
    }

    public static Task search(String query) {
        return new Task() {
            @Override
            public void performAs(Actor actor) {
                SurfTheWeb ability = actor.uses(SurfTheWeb.class);
                WebDriver driver = ability.getWebDriver();
                WebElement element = driver.findElement(By.name("search"));
                element.sendKeys(query);
                element.sendKeys(Keys.ENTER);
            }
        };
    }

}
